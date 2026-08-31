package com.example.mymovieapp.data.service.movie

import com.example.mymovieapp.data.model.movie.MovieListDTO
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): Response<MovieListDTO>
}