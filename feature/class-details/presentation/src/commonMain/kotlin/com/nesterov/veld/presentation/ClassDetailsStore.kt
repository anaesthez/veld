package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface ClassDetailsStore :
    Store<ClassDetailsStore.Intent, ClassDetailsStore.State, ClassDetailsStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val field: String,
    )
}