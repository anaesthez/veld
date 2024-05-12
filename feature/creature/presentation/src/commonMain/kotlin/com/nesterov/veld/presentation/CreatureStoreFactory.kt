package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapperScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nesterov.veld.common.ResultHolder
import com.nesterov.veld.presentation.di.CreatureDependencies
import com.nesterov.veld.presentation.mapper.toCreaturePresentationModel
import com.nesterov.veld.presentation.model.CreaturePresentationModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMviKotlinApi::class)
class CreatureStoreFactory(
    private val index: String,
    private val storeFactory: StoreFactory,
    private val dependencies: CreatureDependencies,
) : CreatureDependencies by dependencies {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): CreatureStore =
        object : CreatureStore,
            Store<CreatureStore.Intent, CreatureStore.State, CreatureStore.Label>
            by storeFactory.create(
                name = STORE_NAME,
                initialState = CreatureStore.State(screenState = CreatureStore.ScreenState.Loading),
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
            when (val result = fetchCreatureUseCase(index).status) {
                is ResultHolder.Error -> withContext(dispatcher.mainDispatcher) {
                    dispatch(Action.FetchCreatureListFailure)
                }

                is ResultHolder.Loading -> withContext(dispatcher.mainDispatcher) {
                    dispatch(Action.FetchCreatureListLoading)
                }

                is ResultHolder.Success -> withContext(dispatcher.mainDispatcher) {
                    dispatch(
                        Action.FetchCreatureListSuccess(
                            result.data.toCreaturePresentationModel()
                        )
                    )
                }
            }
        }
    }

    private sealed interface Action {
        data object FetchCreatureListFailure : Action
        data object FetchCreatureListLoading : Action
        data class FetchCreatureListSuccess(val creatures: CreaturePresentationModel) : Action
    }

    private sealed interface Msg {
        data object FetchCreatureListFailure : Msg
        data object FetchCreatureListLoading : Msg
        data class FetchCreatureListSuccess(val creature: CreaturePresentationModel) : Msg
    }

    private object ReducerImpl : Reducer<CreatureStore.State, Msg> {
        override fun CreatureStore.State.reduce(msg: Msg): CreatureStore.State =
            when (msg) {
                Msg.FetchCreatureListFailure -> {
                    copy(screenState = CreatureStore.ScreenState.Failure)
                }

                Msg.FetchCreatureListLoading -> {
                    copy(screenState = CreatureStore.ScreenState.Loading)
                }

                is Msg.FetchCreatureListSuccess -> {
                    copy(
                        screenState = CreatureStore.ScreenState.Success(creature = msg.creature)
                    )
                }
            }
    }

    private companion object {
        private const val STORE_NAME = "BESTIARY_STORE"
    }
}