package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.nesterov.veld.presentation.model.SpellDetailsPresentationModel

interface SpellDetailsStore : Store<SpellDetailsStore.Intent, SpellDetailsStore.State, SpellDetailsStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val screenState: ScreenState,
    )

    sealed interface ScreenState {
        data object Loading: ScreenState
        data object Failure: ScreenState
        data class Success(val spell: SpellDetailsPresentationModel): ScreenState
    }
}