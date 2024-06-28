package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapperScope
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutorScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nesterov.veld.common.ResultHolder
import com.nesterov.veld.domain.model.SpellDomainModel
import com.nesterov.veld.presentation.di.SpellDependencies
import com.nesterov.veld.presentation.mapper.toSpellPresentationModel
import com.nesterov.veld.presentation.model.SpellPresentationModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                onAction<SpellStore.Action.FetchSpellListFailure> { dispatch(Msg.FetchSpellListFailure) }
                onAction<SpellStore.Action.FetchSpellListLoading> { dispatch(Msg.FetchSpellListLoading) }
                onAction<SpellStore.Action.FetchSpellListSuccess> {
                    dispatch(
                        Msg.FetchSpellListSuccess(
                            spellList = it.spellList
                        )
                    )
                }
                onIntent<SpellStore.Intent.SearchSpell> {
                    when {
                        it.query.isBlank() && ReducerImpl.backupSpellList == null -> {
                            refreshSpellList(
                                failure = Msg.FetchSpellListFailure,
                                loading = Msg.FetchSpellListLoading,
                                success = { spellList ->
                                    Msg.FetchSpellListSuccess(spellList.toSpellPresentationModel())
                                }
                            )
                        }
                        it.query.isBlank() && ReducerImpl.backupSpellList != null -> {
                            dispatch(Msg.RefreshSpellList)
                        }
                        else -> {
                            dispatch(Msg.SearchSpell(it.query))
                        }
                    }
                }
            },
            reducer = ReducerImpl
        ) { }

    private var loadSpellList: Job? = null
    private fun CoroutineBootstrapperScope<SpellStore.Action>.loadSpellList() {
        if (loadSpellList?.isActive == true) return

        loadSpellList = launch {
            withContext(dispatcher.ioDispatcher) {
                when (val result = fetchSpellListUseCase().status) {
                    is ResultHolder.Error -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(SpellStore.Action.FetchSpellListFailure)
                        }
                    }

                    is ResultHolder.Initial -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(SpellStore.Action.FetchSpellListLoading)
                        }
                    }

                    is ResultHolder.Success -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(
                                SpellStore.Action.FetchSpellListSuccess(spellList = result.data.toSpellPresentationModel())
                            )
                        }
                    }
                }
            }
        }
    }

    private var refreshSpellList: Job? = null
    fun <State : Any, Message : Any, Action : Any, Label : Any> CoroutineExecutorScope<State, Message, Action, Label>.refreshSpellList(
        failure: Message,
        loading: Message,
        success: (List<SpellDomainModel>) -> Message,
    ) {
        if (refreshSpellList?.isActive == true) return

        refreshSpellList = launch {
            withContext(dispatcher.ioDispatcher) {
                when (val result = fetchSpellListUseCase().status) {
                    is ResultHolder.Error -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(failure)
                        }
                    }

                    is ResultHolder.Initial -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(loading)
                        }
                    }

                    is ResultHolder.Success -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(success(result.data))
                        }
                    }
                }
            }
        }
    }

    private sealed interface Msg {
        data class SearchSpell(val query: String): Msg
        data object RefreshSpellList: Msg
        data object FetchSpellListFailure: Msg
        data object FetchSpellListLoading: Msg
        data class FetchSpellListSuccess(val spellList: ImmutableList<SpellPresentationModel>): Msg
    }

    private object ReducerImpl : Reducer<SpellStore.State, Msg> {
        var backupSpellList: ImmutableList<SpellPresentationModel>? = null

        override fun SpellStore.State.reduce(msg: Msg): SpellStore.State =
            when(msg) {
                is Msg.FetchSpellListFailure -> copy(screenState = SpellStore.ScreenState.Failure)
                is Msg.FetchSpellListLoading -> copy(screenState = SpellStore.ScreenState.Loading)
                is Msg.FetchSpellListSuccess -> {
                    backupSpellList = msg.spellList
                    copy(screenState = SpellStore.ScreenState.Success(msg.spellList))
                }
                is Msg.SearchSpell -> {
                    val successState = screenState as? SpellStore.ScreenState.Success
                    if (successState != null && backupSpellList != null) {
                        copy(
                            screenState = successState.copy(
                                spellsList = backupSpellList?.filter { spell ->
                                    spell.name.startsWith(
                                        prefix = msg.query,
                                        ignoreCase = true,
                                    ) || spell.level.toString() == msg.query
                                }?.toImmutableList() ?: successState.spellsList
                            )
                        )
                    } else {
                        copy()
                    }
                }

                Msg.RefreshSpellList -> {
                    val successState = screenState as? SpellStore.ScreenState.Success
                    if (successState != null && backupSpellList != null) {
                        copy(
                            screenState = successState.copy(
                                spellsList = backupSpellList ?: successState.spellsList
                            )
                        )
                    } else {
                        copy()
                    }
                }
            }
    }

    private companion object {
        private const val STORE_NAME = "Roll store"
    }
}