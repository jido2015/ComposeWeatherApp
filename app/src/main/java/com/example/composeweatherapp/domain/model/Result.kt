package com.example.composeweatherapp.domain.model

// Sealed class to represent the result of a data operation
sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    class Error(val message: String) : Result<Nothing>()
    data object Loading : Result<Nothing>()
    data object Empty : Result<Nothing>()


}