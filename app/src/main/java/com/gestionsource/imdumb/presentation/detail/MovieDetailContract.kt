package com.gestionsource.imdumb.presentation.detail

import com.gestionsource.core.base.BasePresenter
import com.gestionsource.core.base.BaseView
import com.gestionsource.imdumb.domain.model.MovieDetail

interface MovieDetailContract {

    interface View : BaseView {
        fun showMovie(movie: MovieDetail)
    }

    interface Presenter : BasePresenter {
        fun loadMovie(movieId: Int)
    }
}