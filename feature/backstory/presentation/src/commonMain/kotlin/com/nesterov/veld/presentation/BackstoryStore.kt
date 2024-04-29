package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface BackstoryStore : Store<BackstoryStore.Intent, BackstoryStore.State, BackstoryStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val field: String,
    )
}