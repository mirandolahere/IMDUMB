package com.gestionsource.imdumb.domain.usecase

import com.gestionsource.imdumb.domain.model.MovieDetail
import com.gestionsource.imdumb.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun execute(movieId: Int): Single<MovieDetail> {
        return movieRepository.getMovieDetail(movieId)
    }
}