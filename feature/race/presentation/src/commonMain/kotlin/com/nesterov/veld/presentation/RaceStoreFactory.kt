package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory

class RaceStoreFactory(
    private val storeFactory: StoreFactory,
) {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): RaceStore =
        object : RaceStore, Store<RaceStore.Intent, RaceStore.State, RaceStore.Label>
        by storeFactory.create(
            name = STORE_NAME,
            initialState = RaceStore.State(""),
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

    private object ReducerImpl : Reducer<RaceStore.State, Msg> {
        override fun RaceStore.State.reduce(msg: Msg): RaceStore.State =
            when(msg) {
                else -> copy()
            }
    }

    private companion object {
        private const val STORE_NAME = "Race store"
    }
}