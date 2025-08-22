package com.example.randomuser.data.network

import okhttp3.logging.HttpLoggingInterceptor

object ApiConstants {
    // Base URL
    const val BASE_URL = "https://randomuser.me/"

    // Logging
    val LOGGING_LEVEL = HttpLoggingInterceptor.Level.BODY
}