package com.jaguh.movies.moviesList.presentation

sealed interface MovieListUiEvent{
	data class Paginate(val category: String) : MovieListUiEvent
	object Navigate: MovieListUiEvent
}