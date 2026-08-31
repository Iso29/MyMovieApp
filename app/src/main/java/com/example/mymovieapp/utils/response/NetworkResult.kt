package com.example.mymovieapp.utils.response

sealed class NetworkResult<out T> {

    data class Success<T>(
        val data: T
    ) : NetworkResult<T>()

    data class Error(
        val errorData: ErrorResponse
    ) : NetworkResult<Nothing>()
}