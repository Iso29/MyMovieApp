package com.example.mymovieapp.repository.movie

import com.example.mymovieapp.data.model.movie.MovieListDTO
import com.example.mymovieapp.utils.response.NetworkResult

interface MovieRepository {
    suspend fun getPopularMovies(
        lang: String,
        page: Int,
    ): NetworkResult<MovieListDTO>
}