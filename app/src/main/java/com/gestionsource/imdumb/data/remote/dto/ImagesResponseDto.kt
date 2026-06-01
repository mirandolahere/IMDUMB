package com.gestionsource.imdumb.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImagesResponseDto(
    @SerializedName("backdrops")
    val backdrops: List<ImageDto>,

    @SerializedName("posters")
    val posters: List<ImageDto>
)

data class ImageDto(
    @SerializedName("file_path")
    val filePath: String?
)