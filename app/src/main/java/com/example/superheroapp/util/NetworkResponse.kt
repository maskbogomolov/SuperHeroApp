package com.example.superheroapp.util

import kotlin.coroutines.cancellation.CancellationException

sealed class NetworkResponse<out S, out E> {

    data class Success<out S>(val result: S) : NetworkResponse<S, Nothing>()

    data class Error<out E>(val result: E) : NetworkResponse<Nothing, E>()
}

inline fun <S, R> S.catchingResponse(block: S.() -> R): NetworkResponse<R, Throwable> {
    return try {
        NetworkResponse.Success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        NetworkResponse.Error(e)
    }
}
inline fun <S, E> NetworkResponse<S, E>.doOnError(block: (E) -> Unit): NetworkResponse<S, E> {
    if (this is NetworkResponse.Error) {
        block(this.result)
    }
    return this
}