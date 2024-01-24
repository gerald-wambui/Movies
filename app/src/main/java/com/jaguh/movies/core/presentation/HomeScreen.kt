package com.jaguh.movies.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jaguh.movies.moviesList.presentation.MovieListUiEvent
import com.jaguh.movies.moviesList.presentation.MovieListViewModel


@OptIn(ExperimentalMaterial3Api::class)
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


@Composable
fun BottomNavigationBar(
	bottomNavController: NavHostController,
	onEvent: (MovieListUiEvent) -> Unit
) {

	val items = listOf(
		BottomItem(
			title = "Popular",
			icon = Icons.Rounded.Movie
		),
		BottomItem(
			title = "Upcoming",
			icon = Icons.Rounded.Upcoming
		)
	)

	val selected = rememberSaveable {
		mutableIntStateOf(0)

	}
}


data class BottomItem(
	val title: String,
	val icon: ImageVector
)



















