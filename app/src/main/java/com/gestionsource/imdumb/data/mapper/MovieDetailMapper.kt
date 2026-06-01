package com.gestionsource.imdumb.data.mapper

import com.gestionsource.imdumb.data.remote.dto.CastDto
import com.gestionsource.imdumb.data.remote.dto.ImageDto
import com.gestionsource.imdumb.data.remote.dto.MovieDetailDto
import com.gestionsource.imdumb.domain.model.Actor
import com.gestionsource.imdumb.domain.model.MovieDetail

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun CastDto.toDomain(): Actor {
    return Actor(
        name = name.orEmpty()
    )
}

fun ImageDto.toImageUrl(): String {
    return filePath?.let { IMAGE_BASE_URL + it }.orEmpty()
}

fun MovieDetailDto.toDomain(
    imageUrls: List<String>,
    actors: List<Actor>
): MovieDetail {
    return MovieDetail(
        id = id,
        title = title.orEmpty(),
        rating = voteAverage ?: 0.0,
        overviewHtml = overview.orEmpty(),
        imageUrls = imageUrls,
        actors = actors
    )
}