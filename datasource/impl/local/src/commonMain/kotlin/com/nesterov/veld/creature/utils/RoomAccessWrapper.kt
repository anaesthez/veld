package com.nesterov.veld.creature.utils

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.ResultHolder

suspend fun <T> wrapRoomAccessExceptions(block: suspend () -> T): RequestResult<T> {
    return try {
        RequestResult(status = ResultHolder.Success(block()))
    } catch (e: Exception) {
        e.printStackTrace()
        RequestResult(status = ResultHolder.Error(e))
    }
}