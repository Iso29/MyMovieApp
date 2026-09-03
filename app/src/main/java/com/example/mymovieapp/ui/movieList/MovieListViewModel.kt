package com.example.mymovieapp.ui.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.model.movie.MovieListDTO
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
    private val _popularMovies = MutableStateFlow<NetworkResult<MovieListDTO>?>(null)
    val popularMovies = _popularMovies.asStateFlow()

    init {
        getAllMovies(1)
    }

    fun getAllMovies(
        page: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.getPopularMovies(
                "en-US",
                page
            )
            _popularMovies.emit(response)
        }
    }

}