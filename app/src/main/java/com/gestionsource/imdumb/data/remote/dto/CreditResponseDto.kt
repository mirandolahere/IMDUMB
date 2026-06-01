package com.gestionsource.imdumb.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CreditsResponseDto(
    @SerializedName("cast")
    val cast: List<CastDto>
)

data class CastDto(
    @SerializedName("name")
    val name: String?
)