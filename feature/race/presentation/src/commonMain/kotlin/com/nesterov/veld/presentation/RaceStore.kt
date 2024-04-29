package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface RaceStore : Store<RaceStore.Intent, RaceStore.State, RaceStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val field: String,
    )
}