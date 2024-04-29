package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory

class BackstoryStoreFactory(
    private val storeFactory: StoreFactory,
) {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): BackstoryStore =
        object : BackstoryStore, Store<BackstoryStore.Intent, BackstoryStore.State, BackstoryStore.Label>
        by storeFactory.create(
            name = STORE_NAME,
            initialState = BackstoryStore.State(""),
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

    private object ReducerImpl : Reducer<BackstoryStore.State, Msg> {
        override fun BackstoryStore.State.reduce(msg: Msg): BackstoryStore.State =
            when(msg) {
                else -> copy()
            }
    }

    private companion object {
        private const val STORE_NAME = "backstory store"
    }
}