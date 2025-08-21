package com.example.randomuser.di.modules

import com.example.randomuser.data.network.ApiConstants
import com.example.randomuser.data.network.RandomUserApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideBaseUrl() : String = ApiConstants.BASE_URL

    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = ApiConstants.LOGGING_LEVEL
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor : HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl : String , okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit : Retrofit) : RandomUserApiService {
        return retrofit.create(RandomUserApiService::class.java)
    }
}