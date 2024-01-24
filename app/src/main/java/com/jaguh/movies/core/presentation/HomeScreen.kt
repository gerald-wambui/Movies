package com.jaguh.movies.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.jaguh.movies.moviesList.util.Screen


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

	NavigationBar {
		Row (
			modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
		){
			items.forEachIndexed { index, bottomItem ->
				NavigationBarItem(
					selected = selected.intValue == index,
					onClick = {
							  selected.intValue = index
						when(selected.intValue) {
							0 -> {
								onEvent(MovieListUiEvent.Navigate)
								bottomNavController.popBackStack()
								bottomNavController.navigate(Screen.PopularMovieList.rout)
							}
						}
					},
					icon = {
						Icon(
							imageVector = bottomItem.icon,
							contentDescription = bottomItem.title,
							tint = MaterialTheme.colorScheme.onBackground
						)
					},
					label = {
						Text(
							text = bottomItem.title,
							color = MaterialTheme.colorScheme.onBackground
							)
					}
				)
			}
		}
	}
}


data class BottomItem(
	val title: String,
	val icon: ImageVector
)



















