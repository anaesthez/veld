package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.nesterov.veld.presentation.model.CharacterClassDetailsPresentationModel

interface ClassDetailsStore :
    Store<ClassDetailsStore.Intent, ClassDetailsStore.State, ClassDetailsStore.Label> {
    sealed interface Intent

    sealed interface Label

    sealed interface Action {
        data object FetchClassDetailsFailure : Action
        data object FetchClassDetailsLoading : Action
        data class FetchClassDetailsSuccess(val details: CharacterClassDetailsPresentationModel) :
            Action
    }

    data class State(
        val screenState: ScreenState,
    )

    sealed interface ScreenState {
        data object Loading : ScreenState
        data object Failure : ScreenState
        data class Success(val classDetails: CharacterClassDetailsPresentationModel) : ScreenState
    }
}