package com.nesterov.veld.network.dnd.config

import com.nesterov.veld.common.RequestResult

interface HttpRequestWrapper {
    suspend fun <T> wrapHttpExceptions(block: suspend () -> T): RequestResult<T>
}