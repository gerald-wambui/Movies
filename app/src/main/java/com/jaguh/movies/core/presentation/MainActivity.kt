package com.jaguh.movies.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jaguh.movies.moviesList.presentation.MovieListViewModel
import com.jaguh.movies.moviesList.util.Screen
import com.jaguh.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MoviesTheme {
				SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {

					//val movieListViewModel = hiltViewModel<MovieListViewModel>()

					val navController = rememberNavController()
					NavHost(
						navController = navController,
						startDestination = Screen.Home.rout
					){
						composable(Screen.Home.rout){
							HomeScreen()
						}

						composable(Screen.Details.rout + "/{movieId}",
							arguments =  listOf(
								navArgument("movieId") {type = NavType.IntType}
							)
							){backStackEntry ->
							//DetailsScreen(backStackEntry)
						}
					}
				}
			}
		}
	}


	@Composable
	private fun SetBarColor(color: Color) {
		val systemUiController = rememberSystemUiController()
		LaunchedEffect(key1 = color){
			systemUiController.setSystemBarsColor(color)
		}
	}



}

