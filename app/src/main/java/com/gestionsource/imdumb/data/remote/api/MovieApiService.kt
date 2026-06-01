package com.gestionsource.imdumb.data.remote.api

import com.gestionsource.imdumb.data.remote.dto.CreditsResponseDto
import com.gestionsource.imdumb.data.remote.dto.GenreResponseDto
import com.gestionsource.imdumb.data.remote.dto.ImagesResponseDto
import com.gestionsource.imdumb.data.remote.dto.MovieDetailDto
import com.gestionsource.imdumb.data.remote.dto.MovieResponseDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("genre/movie/list")
    fun getMovieGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Single<GenreResponseDto>

    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: Int,
        @Query("language") language: String = "es-ES"
    ): Single<MovieResponseDto>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Single<MovieDetailDto>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES"
    ): Single<CreditsResponseDto>

    @GET("movie/{movie_id}/images")
    fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<ImagesResponseDto>
}