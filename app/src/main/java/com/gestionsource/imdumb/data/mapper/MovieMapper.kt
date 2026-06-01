package com.gestionsource.imdumb.data.mapper

import com.gestionsource.imdumb.data.remote.dto.GenreDto
import com.gestionsource.imdumb.data.remote.dto.MovieDto
import com.gestionsource.imdumb.domain.model.Movie
import com.gestionsource.imdumb.domain.model.MovieCategory

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title.orEmpty(),
        posterUrl = posterPath?.let { IMAGE_BASE_URL + it }.orEmpty(),
        rating = voteAverage ?: 0.0
    )
}

fun GenreDto.toDomain(movies: List<Movie>): MovieCategory {
    return MovieCategory(
        id = id,
        name = name,
        movies = movies
    )
}