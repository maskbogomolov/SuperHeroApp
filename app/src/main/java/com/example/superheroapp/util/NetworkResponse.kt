package com.example.superheroapp.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

inline fun <S,E,T>NetworkResponse<S,E>.mapSuccess(block: (S) -> T): NetworkResponse<T,E>{
    return when(this){
        is NetworkResponse.Success -> NetworkResponse.Success(result = block(this.result))
        is NetworkResponse.Error -> NetworkResponse.Error(result = this.result)
    }

}

inline fun <S,E> NetworkResponse<S,E>.doOnSuccess(block: (S) -> Unit):NetworkResponse<S,E>{
    if (this is NetworkResponse.Success){
        block(this.result)
    }
    return this
}

inline fun <T, R> Flow<Iterable<T>>.mapToHeroes(crossinline transform: suspend (T) -> R): Flow<List<R>> =
    map { list ->
        list.map { item ->
            transform(item)
        }
    }