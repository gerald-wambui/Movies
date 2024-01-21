package com.jaguh.movies.moviesList.data.repository

import com.jaguh.movies.moviesList.data.local.movie.MovieDatabase
import com.jaguh.movies.moviesList.data.remote.MovieApi
import com.jaguh.movies.moviesList.domain.model.Movie
import com.jaguh.movies.moviesList.domain.repository.MovieListRepository
import com.jaguh.movies.moviesList.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieListRepositoryImpl  @Inject constructor(
	private val movieApi: MovieApi,
	private val movieDatabase: MovieDatabase
) : MovieListRepository {

	override suspend fun getMovieList(
		forceFetchFromRemote: Boolean,
		category: String,
		page: Int
	): Flow<Resource<List<Movie>>> {
		return flow {
			emit(Resource.Loading(true))

			val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)

			val shouldLoadLocalMovie = localMovieList.isEmpty() && !forceFetchFromRemote

			if (shouldLoadLocalMovie) {
				emit(Resource.Success(
					data = localMovieList
				))
			}
		}
	}


	override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {

	}
}