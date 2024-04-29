package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory

class ClassDetailsStoreFactory(
    private val storeFactory: StoreFactory,
) {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): ClassDetailsStore =
        object : ClassDetailsStore,
            Store<ClassDetailsStore.Intent, ClassDetailsStore.State, ClassDetailsStore.Label>
            by storeFactory.create(
                name = STORE_NAME,
                initialState = ClassDetailsStore.State(""),
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

    private object ReducerImpl : Reducer<ClassDetailsStore.State, Msg> {
        override fun ClassDetailsStore.State.reduce(msg: Msg): ClassDetailsStore.State =
            when (msg) {
                else -> copy()
            }
    }

    private companion object {
        private const val STORE_NAME = "Roll store"
    }
}