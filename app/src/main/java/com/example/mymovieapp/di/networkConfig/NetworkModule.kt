package com.example.mymovieapp.di.networkConfig

import com.example.mymovieapp.data.datasource.remote.NetworkingManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return NetworkingManager.buildOkHttpClient()
    }

    @Provides
    @Singleton
    fun provideMovieApiClient(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return NetworkingManager.buildRetrofitClient(
            baseUrl = NetworkingManager.getMovieApiBaseUrl(),
            okHttpClient = okHttpClient
        )
    }
}