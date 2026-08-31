package com.example.mymovieapp.utils.response

import com.google.gson.annotations.SerializedName

data class BaseErrorData(
    @SerializedName("status_code")
    val statusCode: Int?,

    @SerializedName("status_message")
    val statusMessage: String?,

    @SerializedName("success")
    val isSuccess: Boolean?
)