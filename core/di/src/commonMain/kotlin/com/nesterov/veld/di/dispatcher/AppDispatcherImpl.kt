package com.nesterov.veld.di.dispatcher

import com.nesterov.veld.common.AppDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newFixedThreadPoolContext

@OptIn(ExperimentalCoroutinesApi::class)
object AppDispatcherImpl : AppDispatcher {
    override val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    override val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    override val unconfinedDispatcher: CoroutineDispatcher = Dispatchers.Unconfined
    override val ioDispatcher: CoroutineDispatcher =
        newFixedThreadPoolContext(nThreads = 200, name = "IO")
}