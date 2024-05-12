package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.nesterov.veld.presentation.model.CreatureDetailsPresentationModel

interface CreatureStore : Store<CreatureStore.Intent, CreatureStore.State, CreatureStore.Label> {
    sealed interface Intent {
        data class OnSearchCreature(val query: String) : Intent
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