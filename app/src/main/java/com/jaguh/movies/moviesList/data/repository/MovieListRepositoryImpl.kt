package com.jaguh.movies.moviesList.data.repository

import com.jaguh.movies.moviesList.data.local.movie.MovieDatabase
import com.jaguh.movies.moviesList.data.mappers.toMovie
import com.jaguh.movies.moviesList.data.mappers.toMovieEntity
import com.jaguh.movies.moviesList.data.remote.MovieApi
import com.jaguh.movies.moviesList.domain.model.Movie
import com.jaguh.movies.moviesList.domain.repository.MovieListRepository
import com.jaguh.movies.moviesList.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
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
					data = localMovieList.map {movieEntity ->
						movieEntity.toMovie(category)
					}
				))

				emit(Resource.Loading(false))
				return@flow
			}

			val movieListFromApi = try {
				movieApi.getMoviesList(category, page)
			} catch (e: IOException) {
				e.printStackTrace()
				emit(Resource.Error(message = "Error loading movies"))
				return@flow
			} catch (e: HttpException) {
				e.printStackTrace()
				emit(Resource.Error(message = "Error loading movies"))
				return@flow
			} catch (e: Exception) {
				e.printStackTrace()
				emit(Resource.Error(message = "Error loading movies"))
				return@flow
			}


			val movieEntities = movieListFromApi.results.let {
				it.map { movieDto ->
					movieDto.toMovieEntity(category)
				}
			}

			movieDatabase.movieDao.upsertMovieList(movieEntities)

			emit(Resource.Success(
				movieEntities.map { it.toMovie(category) }
			))
			emit(Resource.Loading(false))

		}
	}


	override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {

	}
}