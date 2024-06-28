package com.nesterov.veld.common

sealed interface ResultHolder<T> {
    data class Success<T>(val data: T): ResultHolder<T>
    data class Error<T>(val error: Throwable): ResultHolder<T>
    class Initial<T> : ResultHolder<T>
}

class RequestResult<T>(val status: ResultHolder<T> = ResultHolder.Initial())

typealias Mapper<T, R> = (value: T) -> R

inline fun <reified T, reified R> RequestResult<T>.map(mapper: Mapper<T, R>): RequestResult<R> {
    return when(this.status) {
        is ResultHolder.Error -> RequestResult(status = ResultHolder.Error(this.status.error))
        is ResultHolder.Initial -> RequestResult(status = ResultHolder.Initial())
        is ResultHolder.Success -> RequestResult(status = ResultHolder.Success(mapper(this.status.data)))
    }
}

inline fun <reified T> RequestResult<T>.getOrNull(): T? = (status as? ResultHolder.Success<T>)?.data

inline fun <reified T> RequestResult<T>.doOnSuccess(block: (T) -> Unit) {
    (status as? ResultHolder.Success<T>)?.data?.let(block)
}

inline fun <reified T> RequestResult<T>.doOnFailure(block: () -> Unit) {
    (status as? ResultHolder.Error<T>)?.let { block() }
}

inline fun <reified T> RequestResult<T>.doOnInitial(block: () -> Unit) {
    (status as? ResultHolder.Initial<T>)?.let { block() }
}