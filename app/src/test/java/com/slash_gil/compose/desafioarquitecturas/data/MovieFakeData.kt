package com.slash_gil.compose.desafioarquitecturas.data

const val ANY_MOVIE_TITLE = "Title"
const val ANY_MOVIE_OVERVIEW = "Overview"
const val ANY_MOVIE_POSTER_PATH = "poster_path"
const val ANY_MOVIE_FAVORITE = false

fun givenLocalMovieList() = listOf(givenLocalMovie())

fun givenRemoteMovieList() = listOf(givenRemoteMovie())

fun givenLocalMovie() = Movie(
        id = 1,
        title = ANY_MOVIE_TITLE,
        overview = ANY_MOVIE_OVERVIEW,
        posterPath = ANY_MOVIE_POSTER_PATH,
        favorite = ANY_MOVIE_FAVORITE)

fun givenRemoteMovie() = Movie(
        id = 2,
        title = ANY_MOVIE_TITLE,
        overview = ANY_MOVIE_OVERVIEW,
        posterPath = ANY_MOVIE_POSTER_PATH,
        favorite = ANY_MOVIE_FAVORITE)
