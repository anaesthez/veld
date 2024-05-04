package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.nesterov.veld.presentation.model.CreaturePresentationModel
import kotlinx.collections.immutable.ImmutableList

interface BestiaryStore : Store<BestiaryStore.Intent, BestiaryStore.State, BestiaryStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val screenState: ScreenState,
    )

    sealed interface ScreenState {
        data object Loading : ScreenState
        data object Failure : ScreenState
        data class Success(val creatures: ImmutableList<CreaturePresentationModel>) : ScreenState
    }
}