package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapperScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nesterov.veld.common.ResultHolder
import com.nesterov.veld.presentation.di.BestiaryDependencies
import com.nesterov.veld.presentation.model.CreaturePresentationModel
import com.nesterov.veld.presentation.utils.LocalList
import com.nesterov.veld.presentation.utils.RemoteList
import com.nesterov.veld.presentation.utils.mergeCreaturesList
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@OptIn(ExperimentalMviKotlinApi::class)
class BestiaryStoreFactory(
    private val storeFactory: StoreFactory,
    private val dependencies: BestiaryDependencies,
) : BestiaryDependencies by dependencies {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): BestiaryStore =
        object : BestiaryStore,
            Store<BestiaryStore.Intent, BestiaryStore.State, BestiaryStore.Label>
            by storeFactory.create(
                name = STORE_NAME,
                initialState = BestiaryStore.State(screenState = BestiaryStore.ScreenState.Loading),
                bootstrapper = coroutineBootstrapper {
                    loadCreatureList()
                },
                executorFactory = coroutineExecutorFactory {
                    onAction<Action.FetchCreatureListLoading> { dispatch(Msg.FetchCreatureListLoading) }
                    onAction<Action.FetchCreatureListFailure> { dispatch(Msg.FetchCreatureListFailure) }
                    onAction<Action.FetchCreatureListSuccess> {
                        dispatch(
                            Msg.FetchCreatureListSuccess(
                                it.creatures
                            )
                        )
                    }
                    onIntent<BestiaryStore.Intent.OnSearchCreature> {
                        dispatch(
                            Msg.OnSearchCreature(
                                it.query
                            )
                        )
                    }
                    onIntent<BestiaryStore.Intent.OnAddCreature> { }
                    onIntent<BestiaryStore.Intent.OnDeleteCreature> { }
                },
                reducer = ReducerImpl
            ) {}

    private var loadCreatureListJob: Job? = null
    private fun CoroutineBootstrapperScope<Action>.loadCreatureList() {
        if (loadCreatureListJob?.isActive == true) return

        loadCreatureListJob = launch(
            CoroutineExceptionHandler(handler = { _, _ -> handleCoroutineException() })
        ) {

            val loadRemoteCreatureList: Deferred<RemoteList>?
            loadRemoteCreatureList = async(dispatcher.ioDispatcher) {
                fetchCreatureRemoteListUseCase()
            }

            val loadLocalCreatureList: Deferred<LocalList>?
            loadLocalCreatureList = async(dispatcher.ioDispatcher) {
                fetchCreatureLocalListUseCase()
            }

            when (val result = mergeCreaturesList(
                localList = loadLocalCreatureList.await(),
                remoteList = loadRemoteCreatureList.await()
            ).status) {
                is ResultHolder.Error -> {
                    dispatch(Action.FetchCreatureListFailure)
                }

                is ResultHolder.Initial -> {
                    dispatch(Action.FetchCreatureListLoading)
                }

                is ResultHolder.Success -> {
                    dispatch(Action.FetchCreatureListSuccess(result.data))
                }
            }
        }
    }

    private fun CoroutineBootstrapperScope<Action>.handleCoroutineException() {
        dispatch(Action.FetchCreatureListFailure)
    }

    private sealed interface Action {
        data object FetchCreatureListFailure : Action
        data object FetchCreatureListLoading : Action
        data class FetchCreatureListSuccess(val creatures: List<CreaturePresentationModel>) : Action
    }

    private sealed interface Msg {
        data class OnSearchCreature(val query: String) : Msg
        data object FetchCreatureListFailure : Msg
        data object FetchCreatureListLoading : Msg
        data class FetchCreatureListSuccess(val creatures: List<CreaturePresentationModel>) : Msg
    }

    private object ReducerImpl : Reducer<BestiaryStore.State, Msg> {
        var backupCreaturesList: ImmutableList<CreaturePresentationModel>? = null

        override fun BestiaryStore.State.reduce(msg: Msg): BestiaryStore.State =
            when (msg) {
                Msg.FetchCreatureListFailure -> copy(screenState = BestiaryStore.ScreenState.Failure)
                Msg.FetchCreatureListLoading -> copy(screenState = BestiaryStore.ScreenState.Loading)
                is Msg.FetchCreatureListSuccess -> {
                    val creaturesList = msg.creatures.toImmutableList()
                    backupCreaturesList = creaturesList
                    copy(
                        screenState = BestiaryStore.ScreenState.Success(
                            creatures = creaturesList
                        )
                    )
                }

                is Msg.OnSearchCreature -> {
                    val successState = screenState as? BestiaryStore.ScreenState.Success
                    if (successState != null && backupCreaturesList != null) {
                        copy(
                            screenState = successState.copy(
                                creatures = backupCreaturesList?.filter { spell ->
                                    spell.name.startsWith(
                                        prefix = msg.query,
                                        ignoreCase = true,
                                    )
                                }?.toImmutableList() ?: successState.creatures
                            )
                        )
                    } else {
                        copy()
                    }
                }
            }
    }

    private companion object {
        private const val STORE_NAME = "BESTIARY_STORE"
    }
}