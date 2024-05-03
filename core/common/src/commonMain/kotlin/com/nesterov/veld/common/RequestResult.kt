package com.nesterov.veld.common

sealed interface ResultHolder<T> {
    data class Success<T>(val data: T): ResultHolder<T>
    data class Error<T>(val error: Throwable): ResultHolder<T>
    class Loading<T>: ResultHolder<T>
}

class RequestResult<T>(val status: ResultHolder<T> = ResultHolder.Loading())

typealias Mapper<T, R> = (value: T) -> R

inline fun <reified T, reified R> RequestResult<T>.map(mapper: Mapper<T, R>): RequestResult<R> {
    return when(this.status) {
        is ResultHolder.Error -> RequestResult(status = ResultHolder.Error(this.status.error))
        is ResultHolder.Loading -> RequestResult(status = ResultHolder.Loading())
        is ResultHolder.Success -> RequestResult(status = ResultHolder.Success(mapper(this.status.data)))
    }
}