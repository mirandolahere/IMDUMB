package com.gestionsource.imdumb.domain.usecase

import com.gestionsource.imdumb.domain.model.MovieCategory
import com.gestionsource.imdumb.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMovieCategoriesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun execute(): Single<List<MovieCategory>> {
        return movieRepository.getMovieCategories()
    }
}