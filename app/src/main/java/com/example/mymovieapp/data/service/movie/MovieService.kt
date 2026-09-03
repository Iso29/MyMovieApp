package com.example.mymovieapp.data.service.movie

import com.example.mymovieapp.data.model.movie.MovieListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") lang: String,
        @Query("page") page: Int,
    ): Response<MovieListDTO>
}