package com.example.mymovieapp.data.model.movie

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    @SerializedName("id")
    val id: Long?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?
)