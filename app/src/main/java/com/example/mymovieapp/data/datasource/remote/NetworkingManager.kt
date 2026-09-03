package com.example.mymovieapp.data.datasource.remote

import android.content.Context
import androidx.core.os.BuildCompat
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.mymovieapp.BuildConfig
import com.example.mymovieapp.utils.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkingManager {
    fun getMovieApiBaseUrl(): String {
        return "https://api.themoviedb.org"
    }

    fun buildOkHttpClient(
        context: Context
    ): OkHttpClient {
        val okHttpBuilder =  OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if(BuildConfig.IS_DEBUG){
            okHttpBuilder
                .addInterceptor(ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context, showNotification = true))
                    .maxContentLength(250_000L)
                    .redactHeaders("Authorization", "Cookie")
                    .alwaysReadResponseBody(true)
                    .build())
        }
        return okHttpBuilder.build()
    }

    fun buildRetrofitClient(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }
}