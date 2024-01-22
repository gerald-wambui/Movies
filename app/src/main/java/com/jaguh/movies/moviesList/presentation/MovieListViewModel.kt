package com.jaguh.movies.moviesList.presentation

import androidx.lifecycle.ViewModel
import com.jaguh.movies.moviesList.domain.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
	private val movieListRepository: MovieListRepository
): ViewModel(){
}