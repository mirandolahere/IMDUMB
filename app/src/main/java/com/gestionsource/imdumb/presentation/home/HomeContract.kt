package com.gestionsource.imdumb.presentation.home

import com.gestionsource.core.base.BasePresenter
import com.gestionsource.core.base.BaseView
import com.gestionsource.imdumb.domain.model.MovieCategory

interface HomeContract {

    interface View : BaseView {
        fun showWelcome (text: String)
        fun showCategories(categories: List<MovieCategory>)
        fun navigateToDetail(movieId: Int)
    }

    interface Presenter : BasePresenter {
        fun loadCategories()
        fun onMovieSelected(movieId: Int)
    }
}