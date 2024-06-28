package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapperScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nesterov.veld.common.ResultHolder
import com.nesterov.veld.presentation.di.SpellDetailsDependencies
import com.nesterov.veld.presentation.mapper.toSpellPresentationModel
import com.nesterov.veld.presentation.model.SpellDetailsPresentationModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMviKotlinApi::class)
class SpellDetailsDetailsStoreFactory(
    private val spellIndex: String,
    private val storeFactory: StoreFactory,
    private val dependencies: SpellDetailsDependencies,
): SpellDetailsDependencies by dependencies {

    fun create(): SpellDetailsStore =
        object : SpellDetailsStore, Store<SpellDetailsStore.Intent, SpellDetailsStore.State, SpellDetailsStore.Label>
        by storeFactory.create(
            name = STORE_NAME,
            initialState = SpellDetailsStore.State(screenState = SpellDetailsStore.ScreenState.Loading),
            bootstrapper = coroutineBootstrapper {
                loadSpell(index = spellIndex)
            },
            executorFactory = coroutineExecutorFactory {
                onAction<Action.FetchSpellFailure> { dispatch(Msg.FetchSpellFailure) }
                onAction<Action.FetchSpellLoading> { dispatch(Msg.FetchSpellLoading) }
                onAction<Action.FetchSpellSuccess> { dispatch(Msg.FetchSpellSuccess(spell = it.spell)) }
            },
            reducer = ReducerImpl
        ) {}

    private var loadSpell: Job? = null
    private fun CoroutineBootstrapperScope<Action>.loadSpell(index: String) {
        if (loadSpell?.isActive == true) return

        loadSpell = launch {
            withContext(dispatcher.ioDispatcher) {
                when(val result = fetchSpellDetailsUseCase(index).status) {
                    is ResultHolder.Error -> withContext(dispatcher.mainDispatcher) {
                        dispatch(Action.FetchSpellFailure)
                    }

                    is ResultHolder.Initial -> withContext(dispatcher.mainDispatcher) {
                        dispatch(Action.FetchSpellLoading)
                    }
                    is ResultHolder.Success ->  withContext(dispatcher.mainDispatcher) {
                        dispatch(Action.FetchSpellSuccess(result.data.toSpellPresentationModel()))
                    }
                }
            }
        }
    }

    private sealed interface Action {
        data object FetchSpellFailure: Action
        data object FetchSpellLoading: Action
        data class FetchSpellSuccess(val spell: SpellDetailsPresentationModel): Action
    }

    private sealed interface Msg {
        data object FetchSpellFailure: Msg
        data object FetchSpellLoading: Msg
        data class FetchSpellSuccess(val spell: SpellDetailsPresentationModel): Msg
    }

    private object ReducerImpl : Reducer<SpellDetailsStore.State, Msg> {
        override fun SpellDetailsStore.State.reduce(msg: Msg): SpellDetailsStore.State =
            when (msg) {
                is Msg.FetchSpellFailure -> copy(screenState = SpellDetailsStore.ScreenState.Failure)
                is Msg.FetchSpellLoading -> copy(screenState = SpellDetailsStore.ScreenState.Loading)
                is Msg.FetchSpellSuccess -> copy(
                    screenState = SpellDetailsStore.ScreenState.Success(
                        spell = msg.spell,
                    )
                )
            }
    }

    private companion object {
        private const val STORE_NAME = "Roll store"
    }
}