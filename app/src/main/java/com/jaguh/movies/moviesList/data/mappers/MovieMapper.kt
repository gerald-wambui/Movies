package com.jaguh.movies.moviesList.data.mappers

import com.jaguh.movies.moviesList.data.local.movie.MovieEntity
import com.jaguh.movies.moviesList.domain.model.Movie

fun MovieEntity.toMovie(
	category: String
): Movie {
	return Movie(
		backdrop_path = backdrop_path
	)
}