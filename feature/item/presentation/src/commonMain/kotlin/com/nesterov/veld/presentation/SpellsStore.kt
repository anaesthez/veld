package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface SpellsStore : Store<SpellsStore.Intent, SpellsStore.State, SpellsStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val field: String
    )
}