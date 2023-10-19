package com.slash_gil.compose.desafioarquitecturas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.slash_gil.compose.desafioarquitecturas.data.MovieRepository
import com.slash_gil.compose.desafioarquitecturas.data.local.MovieLocalDataSource
import com.slash_gil.compose.desafioarquitecturas.data.local.MoviesDatabase
import com.slash_gil.compose.desafioarquitecturas.data.remote.MovieRemoteDataSource
import com.slash_gil.compose.desafioarquitecturas.ui.screens.home.view.Home

class MainActivity : ComponentActivity() {
    private lateinit var db: MoviesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = MoviesDatabase.getInstance(applicationContext)

        val repository = MovieRepository(
                localDataSource = MovieLocalDataSource(db.moviesDao()),
                remoteDataSource = MovieRemoteDataSource())

        setContent {
            Home(repository)
        }
    }
}
