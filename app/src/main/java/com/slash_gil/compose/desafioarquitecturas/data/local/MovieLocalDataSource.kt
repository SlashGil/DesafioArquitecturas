package com.slash_gil.compose.desafioarquitecturas.data.local

import com.slash_gil.compose.desafioarquitecturas.data.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieLocalDataSource(private val dao: MoviesDao) {

    val movies: Flow<List<Movie>> = dao.getMovies().map { movies ->
        movies.map { it.toMovie() }
    }

    suspend fun updateMovie(movie: Movie) {
        dao.updateMovie(movie.toLocalMovie())
    }

    suspend fun insertAll(movies: List<Movie>) {
        dao.insertAll(movies.map { it.toLocalMovie() })
    }

    suspend fun count(): Int {
        return dao.count()
    }

}
