package com.gestionsource.imdumb.presentation.splash

import com.gestionsource.core.scheduler.SchedulerProvider
import com.gestionsource.imdumb.domain.usecase.LoadAppConfigUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val loadAppConfigUseCase: LoadAppConfigUseCase,
    private val schedulerProvider: SchedulerProvider
) : SplashContract.Presenter {

    private var view: SplashContract.View? = null
    private val disposable = CompositeDisposable()

    fun attach(view: SplashContract.View) {
        this.view = view
    }

    override fun loadConfiguration() {
        view?.showLoading()

        disposable.add(
            loadAppConfigUseCase.execute()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.main())
                .subscribe(
                    {
                        view?.hideLoading()
                        view?.navigateToHome()
                    },
                    {
                        view?.hideLoading()
                        view?.navigateToHome()
                    }
                )
        )
    }

    override fun onDestroy() {
        disposable.clear()
        view = null
    }
}