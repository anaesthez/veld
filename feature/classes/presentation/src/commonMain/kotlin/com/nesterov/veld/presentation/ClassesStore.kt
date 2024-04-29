package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface ClassesStore : Store<ClassesStore.Intent, ClassesStore.State, ClassesStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val field: String
    )
}