package com.jaguh.movies.moviesList.domain.repository

import java.util.concurrent.Flow

interface MovieListRepository {

	suspend fun getMovieList(
		forceFetchFromRemote: Boolean,
		category: String,
		page: Int
	): Flow<>
}