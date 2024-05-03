package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapperScope
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import com.nesterov.veld.common.ResultHolder
import com.nesterov.veld.presentation.di.ClassDetailsDependencies
import com.nesterov.veld.presentation.mapper.toCharacterClassDetailsPresentationModel
import com.nesterov.veld.presentation.model.CharacterClassDetailsPresentationModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMviKotlinApi::class)
class ClassDetailsStoreFactory(
    private val index: String,
    private val storeFactory: StoreFactory,
    private val classDetailsDependencies: ClassDetailsDependencies,
) : ClassDetailsDependencies by classDetailsDependencies {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): ClassDetailsStore =
        object : ClassDetailsStore,
            Store<ClassDetailsStore.Intent, ClassDetailsStore.State, ClassDetailsStore.Label>
            by storeFactory.create(
                name = STORE_NAME,
                initialState = ClassDetailsStore.State(ClassDetailsStore.ScreenState.Loading),
                bootstrapper = coroutineBootstrapper {
                    loadClassDetails(index = index)
                },
                executorFactory = coroutineExecutorFactory {
                    onAction<ClassDetailsStore.Action.FetchClassDetailsLoading> { dispatch(Msg.FetchClassDetailsLoading) }
                    onAction<ClassDetailsStore.Action.FetchClassDetailsFailure> { dispatch(Msg.FetchClassDetailsFailure) }
                    onAction<ClassDetailsStore.Action.FetchClassDetailsSuccess> {
                        dispatch(
                            Msg.FetchClassDetailsSuccess(
                                it.details
                            )
                        )
                    }
                },
                reducer = ReducerImpl
            ) {}

    private var loadSpellList: Job? = null
    private fun CoroutineBootstrapperScope<ClassDetailsStore.Action>.loadClassDetails(index: String) {
        if (loadSpellList?.isActive == true) return

        loadSpellList = launch {
            withContext(dispatcher.ioDispatcher) {
                when (val result = fetchClassDetails(index).status) {
                    is ResultHolder.Error -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(ClassDetailsStore.Action.FetchClassDetailsFailure)
                        }
                    }

                    is ResultHolder.Loading -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(ClassDetailsStore.Action.FetchClassDetailsLoading)
                        }
                    }

                    is ResultHolder.Success -> {
                        withContext(dispatcher.mainDispatcher) {
                            dispatch(
                                ClassDetailsStore.Action.FetchClassDetailsSuccess(
                                    details = result.data.toCharacterClassDetailsPresentationModel()
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private sealed interface Msg {
        data object FetchClassDetailsFailure : Msg
        data object FetchClassDetailsLoading : Msg
        data class FetchClassDetailsSuccess(val details: CharacterClassDetailsPresentationModel) :
            Msg
    }

    private object ReducerImpl : Reducer<ClassDetailsStore.State, Msg> {
        override fun ClassDetailsStore.State.reduce(msg: Msg): ClassDetailsStore.State =
            when (msg) {
                Msg.FetchClassDetailsFailure -> copy(screenState = ClassDetailsStore.ScreenState.Failure)
                Msg.FetchClassDetailsLoading -> copy(screenState = ClassDetailsStore.ScreenState.Loading)
                is Msg.FetchClassDetailsSuccess -> copy(
                    screenState = ClassDetailsStore.ScreenState.Success(
                        msg.details
                    )
                )
            }
    }

    private companion object {
        private const val STORE_NAME = "CLASS_DETAILS_STORE"
    }
}