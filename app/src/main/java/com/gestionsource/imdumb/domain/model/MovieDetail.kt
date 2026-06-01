package com.gestionsource.imdumb.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val rating: Double,
    val overviewHtml: String,
    val imageUrls: List<String>,
    val actors: List<Actor>
)