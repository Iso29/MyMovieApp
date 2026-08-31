package com.example.mymovieapp.repository.movie

import com.example.mymovieapp.data.model.movie.MovieListDTO
import com.example.mymovieapp.data.service.movie.MovieService
import com.example.mymovieapp.repository.BaseRepository
import com.example.mymovieapp.utils.response.NetworkResult
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository, BaseRepository() {
    override suspend fun getPopularMovies(): NetworkResult<MovieListDTO> {
        return handleNetworkResult {
            movieService.getPopularMovies()
        }
    }
}