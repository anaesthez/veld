package com.nesterov.veld.common

open class AppException(
    message: String? = "",
    cause: Throwable? = null,
) : Exception(message, cause)