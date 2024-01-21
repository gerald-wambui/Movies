package com.jaguh.movies.di

import com.jaguh.movies.moviesList.data.repository.MovieListRepositoryImpl
import com.jaguh.movies.moviesList.domain.repository.MovieListRepository
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
	abstract fun bindMovieListRepository(
		movieListRepositoryImpl: MovieListRepositoryImpl
	): MovieListRepository
}