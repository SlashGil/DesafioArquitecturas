package com.slash_gil.compose.desafioarquitecturas.data

import com.slash_gil.compose.desafioarquitecturas.data.local.MovieLocalDataSource
import com.slash_gil.compose.desafioarquitecturas.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MovieRepository(
        private val localDataSource: MovieLocalDataSource,
        private val remoteDataSource: MovieRemoteDataSource
) {

    val movies: Flow<List<Movie>> = localDataSource.movies

    suspend fun updateMovie(movie: Movie) {
        localDataSource.updateMovie(movie)
    }

    suspend fun requestMovies() {
        val isDbEmpty = localDataSource.count() == 0
        if (isDbEmpty) {
            localDataSource.insertAll(remoteDataSource.getMovies())
        }
    }

}
