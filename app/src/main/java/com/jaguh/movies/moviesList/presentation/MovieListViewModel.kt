package com.jaguh.movies.moviesList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaguh.movies.moviesList.domain.repository.MovieListRepository
import com.jaguh.movies.moviesList.util.Category
import com.jaguh.movies.moviesList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
	private val movieListRepository: MovieListRepository
): ViewModel(){

	private var _movieListState = MutableStateFlow(MovieListState())

	val movieListState = _movieListState.asStateFlow()

	init {
		getPopularMovieList(false)
		getUpcomingMovieList(false)
	}

	fun onEvent(event: MovieListUiEvent) {
		when (event) {
			MovieListUiEvent.Navigate -> {
				_movieListState.update {
					it.copy(
						isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen
					)
				}
			}
			is MovieListUiEvent.Paginate -> {
				if (event.category == Category.POPULAR){
					getPopularMovieList(true)
				} else if (event.category == Category.UPCOMING){
					getUpcomingMovieList(true)
				}
			}
		}
	}
	private fun getUpcomingMovieList(forceFetchFromRemote: Boolean) {
		viewModelScope.launch {
			_movieListState.update {
				it.copy(isLoading = true)
			}

			movieListRepository.getMovieList(
				forceFetchFromRemote,
				Category.POPULAR,
				movieListState.value.popularMovieListPage
			).collectLatest { result ->

				when (result) {

					is Resource.Error -> {

					}

					is Resource.Success -> {

					}

					is Resource.Loading -> TODO()
				}
			}
		}
	}

	private fun getPopularMovieList(forceFetchFromRemote: Boolean) {
		TODO("Not yet implemented")
	}
}