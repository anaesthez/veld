package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory

class BestiaryStoreFactory(
    private val storeFactory: StoreFactory,
) {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): BestiaryStore =
        object : BestiaryStore,
            Store<BestiaryStore.Intent, BestiaryStore.State, BestiaryStore.Label>
        by storeFactory.create(
            name = STORE_NAME,
                initialState = BestiaryStore.State(""),
            bootstrapper = reaktiveBootstrapper {

            },
            executorFactory = reaktiveExecutorFactory {

            },
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

    }

    private sealed class Msg {
    }

    private object ReducerImpl : Reducer<BestiaryStore.State, Msg> {
        override fun BestiaryStore.State.reduce(msg: Msg): BestiaryStore.State =
            when(msg) {
                else -> copy()
            }
    }

    private companion object {
        private const val STORE_NAME = "backstory store"
    }
}