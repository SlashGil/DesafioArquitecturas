package com.slash_gil.compose.desafioarquitecturas.data

import com.slash_gil.compose.desafioarquitecturas.data.local.MovieLocalDataSource
import com.slash_gil.compose.desafioarquitecturas.data.remote.MovieRemoteDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verifyBlocking

class MovieRepositoryShould {
    @Test
    fun `When DB is empty, server is called`() {
        val localDataSource = mock<MovieLocalDataSource> {
            onBlocking { count() } doReturn 0
        }
        val remoteDataSource = mock<MovieRemoteDataSource>()
        val repository = MovieRepository(localDataSource, remoteDataSource)

        runBlocking { repository.requestMovies() }

        verifyBlocking(remoteDataSource) { getMovies() }
    }

    @Test
    fun `When DB is empty, movies are saved into DB`() {
        val expectedMovies = givenLocalMovieList()
        val localDataSource = mock<MovieLocalDataSource> {
            onBlocking { count() } doReturn 0
        }
        val remoteDataSource = mock<MovieRemoteDataSource> {
            onBlocking { getMovies() } doReturn expectedMovies
        }
        val repository = MovieRepository(localDataSource, remoteDataSource)

        runBlocking { repository.requestMovies() }

        verifyBlocking(localDataSource) { insertAll(expectedMovies) }
    }

    @Test
    fun `When DB is no empty, remote data source is not called`() {
        val localDataSource = mock<MovieLocalDataSource> {
            onBlocking { count() } doReturn 1
        }
        val remoteDataSource = mock<MovieRemoteDataSource>()
        val repository = MovieRepository(localDataSource, remoteDataSource)

        runBlocking { repository.requestMovies() }

        verifyBlocking(remoteDataSource, times(0)) { getMovies() }
    }

    @Test
    fun `When DB is not empty, movies are recovered from DB`() {
        val localMovies = givenLocalMovieList()
        val remoteMovies = givenRemoteMovieList()
        val localDataSource = mock<MovieLocalDataSource> {
            onBlocking { count() } doReturn 1
            onBlocking { movies } doReturn flowOf(localMovies)
        }
        val remoteDataSource = mock<MovieRemoteDataSource> {
            onBlocking { getMovies() } doReturn remoteMovies
        }
        val repository = MovieRepository(localDataSource, remoteDataSource)

        val result = runBlocking {
            repository.requestMovies()
            repository.movies.first()
        }

        assertEquals(localMovies, result)
    }
}
