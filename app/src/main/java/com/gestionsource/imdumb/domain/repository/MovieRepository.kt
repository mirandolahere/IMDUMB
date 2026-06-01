package com.gestionsource.imdumb.domain.repository

import com.gestionsource.imdumb.domain.model.MovieCategory
import com.gestionsource.imdumb.domain.model.MovieDetail
import io.reactivex.Single

interface MovieRepository {

    fun getMovieCategories(): Single<List<MovieCategory>>

    fun getMovieDetail(movieId: Int): Single<MovieDetail>
}