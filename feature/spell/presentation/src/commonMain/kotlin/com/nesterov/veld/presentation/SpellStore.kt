package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.nesterov.veld.presentation.model.SpellPresentationModel
import kotlinx.collections.immutable.ImmutableList

interface SpellStore : Store<SpellStore.Intent, SpellStore.State, SpellStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val screenState: ScreenState,
    )

    sealed interface ScreenState {
        data object Loading: ScreenState
        data object Failure: ScreenState
        data class Success(val spellsList: ImmutableList<SpellPresentationModel>): ScreenState
    }
}