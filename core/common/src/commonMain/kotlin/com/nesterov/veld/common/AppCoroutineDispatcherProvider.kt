package com.nesterov.veld.common

import kotlinx.coroutines.CoroutineDispatcher

interface AppCoroutineDispatcherProvider {
    val mainDispatcher: CoroutineDispatcher
    val defaultDispatcher: CoroutineDispatcher
    val unconfinedDispatcher: CoroutineDispatcher
    val ioDispatcher: CoroutineDispatcher
}