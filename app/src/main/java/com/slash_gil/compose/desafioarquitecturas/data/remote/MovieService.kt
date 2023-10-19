package com.slash_gil.compose.desafioarquitecturas.data.remote

import retrofit2.http.GET

interface MovieService {
    @GET("discover/movie?api_key=361ab8d6b07bb61720dc8ba2cba89df9")
    suspend fun getMovies(): MovieResult
}
