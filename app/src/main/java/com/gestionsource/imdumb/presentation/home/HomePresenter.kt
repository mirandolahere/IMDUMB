package com.gestionsource.imdumb.presentation.home

import com.gestionsource.core.scheduler.SchedulerProvider
import com.gestionsource.imdumb.domain.usecase.GetMovieCategoriesUseCase
import com.gestionsource.imdumb.domain.usecase.LoadAppConfigUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val getMovieCategoriesUseCase: GetMovieCategoriesUseCase,
    private val schedulerProvider: SchedulerProvider,
    private val loadAppConfigUseCase: LoadAppConfigUseCase
) : HomeContract.Presenter {

    private var view: HomeContract.View? = null
    private val disposable = CompositeDisposable()

    fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun loadCategories() {
        view?.showLoading()

        disposable.add(
            loadAppConfigUseCase.execute()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe(
                    { config -> view?.showWelcome(config.welcome_text) },
                    { view?.showWelcome("Bienvenido a IMDUMB") }
                )
        )

        disposable.add(
            getMovieCategoriesUseCase.execute()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe(
                    { categories ->
                        view?.hideLoading()
                        view?.showCategories(categories)
                    },
                    { error ->
                        view?.hideLoading()
                        view?.showError(error.message ?: "Error al cargar películas")
                    }
                )
        )
    }

    override fun onMovieSelected(movieId: Int) {
        view?.navigateToDetail(movieId)
    }

    override fun onDestroy() {
        disposable.clear()
        view = null
    }
}