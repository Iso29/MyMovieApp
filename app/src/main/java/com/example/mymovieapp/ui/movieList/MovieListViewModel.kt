package com.example.mymovieapp.ui.movieList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.repository.movie.MovieRepository
import com.example.mymovieapp.utils.response.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _popularMovies = MutableStateFlow<List<MovieData>>(emptyList())
    val popularMovies = _popularMovies.asStateFlow()

    fun getAllMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e("ISO_TEST", "Request pending... ")
            val response = movieRepository.getPopularMovies()
            when (response) {
                is NetworkResult.Success -> {
                    Log.e("ISO_TEST", "Movies : ${response.data.movieList}")
                }

                is NetworkResult.Error -> {
                    Log.e("ISO_TEST", "Error : ${response.errorData.message}")
                }
            }
        }
    }

}