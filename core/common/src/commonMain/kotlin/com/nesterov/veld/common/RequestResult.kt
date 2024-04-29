package com.nesterov.veld.common

sealed interface ResultHolder<T> {
    data class Success<T>(val data: T): ResultHolder<T>
    data class Error<T>(val error: Throwable): ResultHolder<T>
    class Loading<T>: ResultHolder<T>
}

class RequestResult<T>(val status: ResultHolder<T> = ResultHolder.Loading())

//inline fun <reified T> RequestResult.Success<T>.onSuccess(block: (T) -> Unit) {
//    block(this.data)
//}

typealias Mapper<T, R> = (value: T) -> R

inline fun <reified T, reified R> RequestResult<T>.map(mapper: Mapper<T, R>): RequestResult<R> {
    return when(this.status) {
        is ResultHolder.Error -> RequestResult(status = ResultHolder.Error(this.status.error))
        is ResultHolder.Loading -> RequestResult(status = ResultHolder.Loading())
        is ResultHolder.Success -> RequestResult(status = ResultHolder.Success(mapper(this.status.data)))
    }
}

//inline fun <reified T, reified R> RequestResult<T>.map(mapper: Mapper<T, R>): RequestResult<R> {
//    return when(this.status) {
//        is RequestResult.Error -> RequestResult(status = ResultHolder.Error(this.status.error))
//        is RequestResult.Loading -> RequestResult(status = ResultHolder.Loading())
//        is RequestResult.Success -> RequestResult(status = ResultHolder.Success(mapper(this.status.data)))
//    }
//}

//inline fun <reified T, reified R> RequestResult<T>.map(mapper: Mapper<T, R>): RequestResult<R> {
//    return when(this) {
//        is RequestResult.Error -> RequestResult.Error(error)
//        is RequestResult.Loading -> RequestResult.Loading()
//        is RequestResult.Success -> RequestResult.Success(data = mapper(data))
//    }
//}


//interface RequestResult<T> {
//    val state: RequestState<T>
//
//    fun onSuccess(): T
//
//    sealed interface RequestState<T> {
//        data class Success<T>(val data: T): RequestState<T>
//        data class Error<T>(val error: Throwable): RequestState<T>
//        class Loading<T>: RequestState<T>
//    }
//}
//
//class RequestResultImpl<T>(
//    override val state: RequestResult.RequestState<T>
//): RequestResult<T> {
//
//    val isLoading: Boolean
//        get() = state is RequestResult.RequestState.Loading
//    val isSuccess: Boolean
//        get() = state is RequestResult.RequestState.Success
//    val isError: Boolean
//        get() = state is RequestResult.RequestState.Error
//
//    override fun onSuccess(): T {
//        return if (state is RequestResult.RequestState.Success) {
//            state.data
//        } else {
//    }
//}
//
//inline fun <reified T, reified R> RequestResult<T>.map(
//    mapper: Mapper<T, R>
//): RequestResult<R> {
//    return when (state) {
//        is RequestResult.RequestState.Error -> RequestResultImpl(
//            state = RequestResult.RequestState.Error(
//                (this.state as RequestResult.RequestState.Error).error
//            )
//        )
//        is RequestResult.RequestState.Loading -> RequestResultImpl(
//            state = RequestResult.RequestState.Loading()
//        )
//        is RequestResult.RequestState.Success -> RequestResultImpl(
//            state = RequestResult.RequestState.Success(
//                data = mapper((this.state as RequestResult.RequestState.Success).data)
//            )
//        )
//    }
//}

//fun <T>RequestResult.RequestState.Success<T>.data() {
//
//}