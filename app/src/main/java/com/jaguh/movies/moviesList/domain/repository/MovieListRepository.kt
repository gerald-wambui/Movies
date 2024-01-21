package com.jaguh.movies.moviesList.domain.repository

import com.jaguh.movies.moviesList.domain.model.Movie
import com.jaguh.movies.moviesList.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {

	suspend fun getMovieList(
		forceFetchFromRemote: Boolean,
		category: String,
		page: Int
	): Flow<Resource<List<Movie>>>


	suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}