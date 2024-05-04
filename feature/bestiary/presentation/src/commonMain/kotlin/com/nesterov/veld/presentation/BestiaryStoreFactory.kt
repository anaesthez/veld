package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapperScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nesterov.veld.common.ResultHolder
import com.nesterov.veld.domain.CreatureDomainModel
import com.nesterov.veld.presentation.di.BestiaryDependencies
import com.nesterov.veld.presentation.mapper.toCreaturePresentationModel
import com.nesterov.veld.presentation.model.CreaturePresentationModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                },
                reducer = ReducerImpl
            ) {}

    private var loadCreatureList: Job? = null
    private fun CoroutineBootstrapperScope<Action>.loadCreatureList() {
        if (loadCreatureList?.isActive == true) return

        loadCreatureList = launch(dispatcher.ioDispatcher) {
            when (val result = fetchCreatureListUseCase().status) {
                is ResultHolder.Error -> withContext(dispatcher.mainDispatcher) {
                    dispatch(Action.FetchCreatureListFailure)
                }

                is ResultHolder.Loading -> withContext(dispatcher.mainDispatcher) {
                    dispatch(Action.FetchCreatureListLoading)
                }

                is ResultHolder.Success -> withContext(dispatcher.mainDispatcher) {
                    dispatch(
                        Action.FetchCreatureListSuccess(
                            result.data.map(
                                CreatureDomainModel::toCreaturePresentationModel
                            )
                        )
                    )
                }
            }
        }
    }

    private sealed interface Action {
        data object FetchCreatureListFailure : Action
        data object FetchCreatureListLoading : Action
        data class FetchCreatureListSuccess(val creatures: List<CreaturePresentationModel>) : Action
    }

    private sealed interface Msg {
        data object FetchCreatureListFailure : Msg
        data object FetchCreatureListLoading : Msg
        data class FetchCreatureListSuccess(val creatures: List<CreaturePresentationModel>) : Msg
    }

    private object ReducerImpl : Reducer<BestiaryStore.State, Msg> {
        override fun BestiaryStore.State.reduce(msg: Msg): BestiaryStore.State =
            when (msg) {
                Msg.FetchCreatureListFailure -> copy(screenState = BestiaryStore.ScreenState.Failure)
                Msg.FetchCreatureListLoading -> copy(screenState = BestiaryStore.ScreenState.Loading)
                is Msg.FetchCreatureListSuccess -> copy(
                    screenState = BestiaryStore.ScreenState.Success(
                        creatures = msg.creatures.toImmutableList()
                    )
                )
            }
    }

    private companion object {
        private const val STORE_NAME = "BESTIARY_STORE"
    }
}