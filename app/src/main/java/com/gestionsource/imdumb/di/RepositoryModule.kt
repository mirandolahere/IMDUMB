package com.gestionsource.imdumb.di

import com.gestionsource.imdumb.data.repository.ConfigRepositoryImpl
import com.gestionsource.imdumb.data.repository.MovieRepositoryImpl
import com.gestionsource.imdumb.domain.repository.ConfigRepository
import com.gestionsource.imdumb.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @Singleton
    abstract fun bindConfigRepository(
        configRepositoryImpl: ConfigRepositoryImpl
    ): ConfigRepository
}