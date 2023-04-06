package com.app.core

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val throwableMessage: String) : Result<Nothing>()
}
