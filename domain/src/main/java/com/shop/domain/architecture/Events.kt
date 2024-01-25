package com.shop.domain.architecture

sealed class Events<T>(val data:T? = null, val message:String? = null) {
    class Success<T>(data: T) : Events<T>(data = data)
    class Error<T>(message: String) : Events<T>(message = message)
}