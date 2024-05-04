package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface BestiaryStore : Store<BestiaryStore.Intent, BestiaryStore.State, BestiaryStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val field: String,
    )
}