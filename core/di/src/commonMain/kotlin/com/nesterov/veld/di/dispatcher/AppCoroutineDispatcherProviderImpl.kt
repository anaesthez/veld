package com.nesterov.veld.di.dispatcher

import com.nesterov.veld.common.AppCoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newFixedThreadPoolContext

class AppCoroutineDispatcherProviderImpl(
    override val mainDispatcher: CoroutineDispatcher,
    override val defaultDispatcher: CoroutineDispatcher,
    override val unconfinedDispatcher: CoroutineDispatcher,
    override val ioDispatcher: CoroutineDispatcher
): AppCoroutineDispatcherProvider {

    companion object {
        @OptIn(ExperimentalCoroutinesApi::class)
        fun createDefault() = AppCoroutineDispatcherProviderImpl(
            mainDispatcher = Dispatchers.Main,
            defaultDispatcher = Dispatchers.Default,
            unconfinedDispatcher = Dispatchers.Unconfined,
            ioDispatcher = newFixedThreadPoolContext(nThreads = 200, name = "IO")
        )
    }
}