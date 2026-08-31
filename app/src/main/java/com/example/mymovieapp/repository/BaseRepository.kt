package com.example.mymovieapp.repository

import com.example.mymovieapp.utils.response.BaseErrorData
import com.example.mymovieapp.utils.response.ErrorResponse
import com.example.mymovieapp.utils.response.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository {
    suspend fun <T> handleNetworkResult(
        request: suspend () -> Response<T>
    ): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = request.invoke()
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    NetworkResult.Success(responseBody)
                } else {
                    val errorView = response.getErrorView()
                    NetworkResult.Error(
                        errorView.toErrorData()
                    )
                }
            } catch (throwable: Throwable) {
                NetworkResult.Error(
                    ErrorResponse(
                        throwable.localizedMessage ?: "", 0
                    )
                )
            }
        }
    }

    internal fun <T> Response<T>.getErrorView(): BaseErrorData {
        return try {
            val errorText = errorBody()?.string()
            Gson().fromJson(errorText, BaseErrorData::class.java)
        } catch (e: Exception) {
            BaseErrorData(statusCode = code(), statusMessage = message(), isSuccess = false)
        }
    }

    internal fun BaseErrorData.toErrorData(): ErrorResponse {
        return ErrorResponse(
            message = this.statusMessage ?: "",
            errorCode = this.statusCode ?: 0
        )
    }
}