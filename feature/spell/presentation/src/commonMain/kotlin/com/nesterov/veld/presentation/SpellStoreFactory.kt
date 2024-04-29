package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapperScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nesterov.veld.common.AppCoroutineDispatcherProvider
import com.nesterov.veld.common.ResultHolder
import com.nesterov.veld.domain.usecases.FetchSpellListUseCase
import com.nesterov.veld.presentation.di.SpellDependencies
import com.nesterov.veld.presentation.mapper.toSpellPresentationModel
import com.nesterov.veld.presentation.model.SpellPresentationModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.suspendCoroutine

@OptIn(ExperimentalMviKotlinApi::class)
class SpellStoreFactory(
    private val storeFactory: StoreFactory,
    private val dependencies: SpellDependencies,
): SpellDependencies by dependencies {

    fun create(): SpellStore =
        object : SpellStore, Store<SpellStore.Intent, SpellStore.State, SpellStore.Label>
        by storeFactory.create(
            name = STORE_NAME,
            initialState = SpellStore.State(screenState = SpellStore.ScreenState.Loading),
            bootstrapper = coroutineBootstrapper {
                loadSpellList()
            },
            executorFactory = coroutineExecutorFactory {
                onAction<Action.FetchSpellListFailure> { dispatch(Msg.FetchSpellListFailure) }
                onAction<Action.FetchSpellListLoading> { dispatch(Msg.FetchSpellListLoading) }
                onAction<Action.FetchSpellListSuccess> { dispatch(Msg.FetchSpellListSuccess(spellList = it.spellList)) }
            },
            reducer = ReducerImpl
        ) { }

    private var loadSpellList: Job? = null
    private fun CoroutineBootstrapperScope<Action>.loadSpellList() {
        if (loadSpellList?.isActive == true) return

        loadSpellList = launch {
            withContext(dispatcher.ioDispatcher) {
                when (val result = fetchSpellListUseCase().status) {
                    is ResultHolder.Error -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(Action.FetchSpellListFailure)
                        }
                    }

                    is ResultHolder.Loading -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(Action.FetchSpellListLoading)
                        }
                    }

                    is ResultHolder.Success -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(
                                Action.FetchSpellListSuccess(spellList = result.data.toSpellPresentationModel())
                            )
                        }
                    }
                }
            }
        }
    }

    private sealed interface Action {
        data object FetchSpellListFailure: Action
        data object FetchSpellListLoading: Action
        data class FetchSpellListSuccess(val spellList: ImmutableList<SpellPresentationModel>): Action
    }

    private sealed interface Msg {
        data object FetchSpellListFailure: Msg
        data object FetchSpellListLoading: Msg
        data class FetchSpellListSuccess(val spellList: ImmutableList<SpellPresentationModel>): Msg
    }

    private object ReducerImpl : Reducer<SpellStore.State, Msg> {
        override fun SpellStore.State.reduce(msg: Msg): SpellStore.State =
            when(msg) {
                is Msg.FetchSpellListFailure -> copy(screenState = SpellStore.ScreenState.Failure)
                is Msg.FetchSpellListLoading -> copy(screenState = SpellStore.ScreenState.Loading)
                is Msg.FetchSpellListSuccess -> copy(screenState = SpellStore.ScreenState.Success(msg.spellList))
            }
    }

    private companion object {
        private const val STORE_NAME = "Roll store"
    }
}