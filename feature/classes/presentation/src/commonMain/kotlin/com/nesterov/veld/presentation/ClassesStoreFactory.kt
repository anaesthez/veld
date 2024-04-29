package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import com.nesterov.veld.model.CharacterClassPresentationModel
import kotlinx.collections.immutable.toImmutableList

class ClassesStoreFactory(
    private val storeFactory: StoreFactory,
    val characterClassesList: List<CharacterClassPresentationModel>,
) {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): ClassesStore =
        object : ClassesStore, Store<ClassesStore.Intent, ClassesStore.State, ClassesStore.Label>
        by storeFactory.create(
            name = STORE_NAME,
            initialState = ClassesStore.State(charClassesList = characterClassesList.toImmutableList()),
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

    private object ReducerImpl : Reducer<ClassesStore.State, Msg> {
        override fun ClassesStore.State.reduce(msg: Msg): ClassesStore.State =
            when(msg) {
                else -> copy()
            }
    }

    private companion object {
        private const val STORE_NAME = "Roll store"
    }
}