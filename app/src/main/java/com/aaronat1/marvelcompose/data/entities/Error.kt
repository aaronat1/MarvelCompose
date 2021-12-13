package com.aaronat1.marvelcompose.data.entities

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import retrofit2.HttpException
import java.io.IOException

typealias Result<T> = Either<Error, T>

sealed class Error {
    class Server(val code: Int) : Error()
    object Conectivity: Error()
    class Unknown(val message: String) : Error()
}

fun Exception.toError() : Error = when(this) {
    is IOException -> Error.Conectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}

inline fun <T> tryCall(action: () -> T) : Result<T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}

