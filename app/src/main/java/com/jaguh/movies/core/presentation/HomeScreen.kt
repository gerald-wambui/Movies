package com.jaguh.movies.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jaguh.movies.moviesList.presentation.MovieListViewModel


@Composable
fun HomeScreen(navController: NavHostController) {

	val movieListViewModel = hiltViewModel<MovieListViewModel>()
	val movieState = movieListViewModel.movieListState.collectAsState().value
	val bottomNavController = rememberNavController()


	Scaffold(
		bottomBar = {

		},
		topBar = {

		}
	) {
		Box (
			modifier = Modifier.padding(it)
		){

		}
	}

}