package com.jaguh.movies.moviesList.util

sealed class Screen (val rout: String) {

	object Home: Screen("main")
	object PopularMovieList: Screen("popularMovie")
	object UpcomingMovieList: Screen("upcomingMovie")
	object Details: Screen("details")
}