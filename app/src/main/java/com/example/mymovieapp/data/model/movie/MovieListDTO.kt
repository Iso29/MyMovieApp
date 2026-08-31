package com.example.mymovieapp.data.model.movie

import com.google.gson.annotations.SerializedName

data class MovieListDTO(
    @SerializedName("results")
    val movieList: List<MovieDTO>?,

    @SerializedName("page")
    val currentPage: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?
)