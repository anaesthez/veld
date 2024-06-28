package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.nesterov.veld.presentation.creature.model.CreatureDetailsPresentationModel

interface SavedStore : Store<SavedStore.Intent, SavedStore.State, SavedStore.Label> {
    sealed interface Intent {

    }

    sealed interface Label

    data class State(
        val screenState: ScreenState,
    )

    sealed interface ScreenState {
        data object Loading : ScreenState
        data object Failure : ScreenState
        data class Success(val creature: CreatureDetailsPresentationModel) : ScreenState
    }
}