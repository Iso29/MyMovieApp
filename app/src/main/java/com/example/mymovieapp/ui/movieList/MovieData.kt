package com.example.mymovieapp.ui.movieList

import com.example.mymovieapp.data.model.movie.MovieDTO

data class MovieData(
    val id: Long,
    val title: String,
    val rating: Double,
    val posterUrl: String
)

fun MovieDTO.toDomainModel(): MovieData {
    return MovieData(
        this.id ?: 0,
        this.title ?: "",
        this.voteAverage ?: 0.0,
        this.posterPath ?: ""
    )
}