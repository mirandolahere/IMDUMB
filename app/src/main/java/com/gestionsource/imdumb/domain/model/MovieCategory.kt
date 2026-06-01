package com.gestionsource.imdumb.domain.model

data class MovieCategory(
    val id: Int,
    val name: String,
    val movies: List<Movie>
)