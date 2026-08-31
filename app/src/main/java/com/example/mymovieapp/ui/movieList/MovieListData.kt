package com.example.mymovieapp.ui.movieList

import com.example.mymovieapp.data.model.movie.MovieListDTO


data class MovieListData(
    val movieList: List<MovieData>,
    val currentPage: Int,
    val totalPages: Int
)

fun MovieListDTO.toDomainModel(): MovieListData {
    return MovieListData(
        this.movieList?.map {
            it.toDomainModel()
        } ?: emptyList(),
        this.currentPage ?: 0,
        this.totalPages ?: 0
    )
}