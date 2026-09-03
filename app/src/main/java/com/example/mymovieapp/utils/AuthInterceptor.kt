package com.example.mymovieapp.utils

import com.example.mymovieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = BuildConfig.API_KEY

        val request = chain.request()
            .newBuilder()
            .apply {
                if (token.isNotBlank()) {
                    header(
                        "accept",
                        "application/json"
                    )
                    header(
                        "Authorization",
                        "Bearer $token"
                    )
                }
            }
            .build()

        return chain.proceed(request)
    }
}