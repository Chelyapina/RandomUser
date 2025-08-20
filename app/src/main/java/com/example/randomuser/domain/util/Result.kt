package com.example.randomuser.domain.util

sealed class Result<out T> {
    data class Success<out T>(val data : T) : Result<T>()
    data class Error(val type : ErrorType) : Result<Nothing>()
}

enum class ErrorType {
    NO_INTERNET ,
    SERVER_ERROR ,
    UNKNOWN_ERROR
}