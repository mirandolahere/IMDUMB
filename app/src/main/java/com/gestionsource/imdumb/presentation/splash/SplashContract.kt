package com.gestionsource.imdumb.presentation.splash

import com.gestionsource.core.base.BasePresenter
import com.gestionsource.core.base.BaseView

interface SplashContract {

    interface View : BaseView {
        fun navigateToHome()
    }

    interface Presenter : BasePresenter {
        fun loadConfiguration()
    }
}