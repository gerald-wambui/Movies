package com.jaguh.movies

import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.jaguh.movies.moviesList.data.local.movie.MovieDatabase
import com.jaguh.movies.moviesList.data.local.movie.MovieEntity
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MovieDaoTest {

    private lateinit var database: MovieDatabase

    @Before
    fun initDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun insertAndGetMovie() = runTest {
        val movie = MovieEntity(
            adult = true,
            backdrop_path = "backdrop",
            genre_ids = "genre",
            original_language = "language",
            original_title = "title",
            overview = "overview",
            popularity = 60.0,
            poster_path = "image",
            release_date = "release",
            title = "title",
            video  = true,
            vote_average = 5.0,
            vote_count = 10,
            id = 1,
            category = "romance"
        )

        database.movieDao.upsertMovieList(List<>)
    }
}