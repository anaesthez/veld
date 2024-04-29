package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory

class SpellsStoreFactory(
    private val storeFactory: StoreFactory,
) {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): SpellsStore =
        object : SpellsStore, Store<SpellsStore.Intent, SpellsStore.State, SpellsStore.Label>
        by storeFactory.create(
            name = STORE_NAME,
            initialState = SpellsStore.State(""),
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

    private object ReducerImpl : Reducer<SpellsStore.State, Msg> {
        override fun SpellsStore.State.reduce(msg: Msg): SpellsStore.State =
            when(msg) {
                else -> copy()
            }
    }

    private companion object {
        private const val STORE_NAME = "Roll store"
    }
}