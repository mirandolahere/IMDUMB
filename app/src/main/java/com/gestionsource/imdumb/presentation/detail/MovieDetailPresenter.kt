package com.gestionsource.imdumb.presentation.detail

import com.gestionsource.core.scheduler.SchedulerProvider
import com.gestionsource.imdumb.domain.usecase.GetMovieDetailUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieDetailPresenter @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val schedulerProvider: SchedulerProvider
) : MovieDetailContract.Presenter {

    private var view: MovieDetailContract.View? = null
    private val disposable = CompositeDisposable()

    fun attach(view: MovieDetailContract.View) {
        this.view = view
    }

    override fun loadMovie(movieId: Int) {

        view?.showLoading()

        disposable.add(
            getMovieDetailUseCase.execute(movieId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe(
                    { movie ->
                        view?.hideLoading()
                        view?.showMovie(movie)
                    },
                    { error ->
                        view?.hideLoading()
                        view?.showError(
                            error.message ?: "Error cargando detalle"
                        )
                    }
                )
        )
    }

    override fun onDestroy() {
        disposable.clear()
        view = null
    }
}