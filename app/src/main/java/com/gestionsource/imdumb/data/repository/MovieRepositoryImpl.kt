package com.gestionsource.imdumb.data.repository

import com.gestionsource.imdumb.BuildConfig
import com.gestionsource.imdumb.data.mapper.toDomain
import com.gestionsource.imdumb.data.mapper.toImageUrl
import com.gestionsource.imdumb.data.remote.api.MovieApiService
import com.gestionsource.imdumb.domain.model.MovieCategory
import com.gestionsource.imdumb.domain.model.MovieDetail
import com.gestionsource.imdumb.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
) : MovieRepository {

    override fun getMovieCategories(): Single<List<MovieCategory>> {
        return movieApiService.getMovieGenres(
            apiKey = BuildConfig.TMDB_API_KEY
        ).flatMap { genreResponse ->

            val categoryRequests = genreResponse.genres
                .take(5)
                .map { genre ->

                    movieApiService.getMoviesByGenre(
                        apiKey = BuildConfig.TMDB_API_KEY,
                        genreId = genre.id
                    ).map { movieResponse ->

                        val movies = movieResponse.results
                            .take(10)
                            .map { movieDto ->
                                movieDto.toDomain()
                            }

                        genre.toDomain(movies)
                    }
                }

            Single.zip(categoryRequests) { categories ->
                categories.map { category ->
                    category as MovieCategory
                }
            }
        }
    }

    override fun getMovieDetail(movieId: Int): Single<MovieDetail> {
        return Single.zip(
            movieApiService.getMovieDetail(
                movieId = movieId,
                apiKey = BuildConfig.TMDB_API_KEY
            ),
            movieApiService.getMovieCredits(
                movieId = movieId,
                apiKey = BuildConfig.TMDB_API_KEY
            ),
            movieApiService.getMovieImages(
                movieId = movieId,
                apiKey = BuildConfig.TMDB_API_KEY
            )
        ) { detailDto, creditsDto, imagesDto ->

            val imageUrls = imagesDto.backdrops
                .take(5)
                .mapNotNull { imageDto ->
                    imageDto.toImageUrl().takeIf { it.isNotEmpty() }
                }

            val actors = creditsDto.cast
                .take(10)
                .map { castDto ->
                    castDto.toDomain()
                }

            detailDto.toDomain(
                imageUrls = imageUrls,
                actors = actors
            )
        }
    }
}