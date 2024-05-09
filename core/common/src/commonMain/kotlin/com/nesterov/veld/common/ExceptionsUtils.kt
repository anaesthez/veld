package com.nesterov.veld.common

suspend fun <T> wrapHttpExceptions(block: suspend () -> T): RequestResult<T> {
    return try {
        RequestResult(status = ResultHolder.Success(block()))
    } catch (e: Exception) {
        e.printStackTrace()
        RequestResult(status = ResultHolder.Error(e))
    }
}