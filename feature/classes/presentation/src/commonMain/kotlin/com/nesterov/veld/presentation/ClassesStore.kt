package com.nesterov.veld.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.nesterov.veld.model.CharacterClassPresentationModel
import kotlinx.collections.immutable.ImmutableList

interface ClassesStore : Store<ClassesStore.Intent, ClassesStore.State, ClassesStore.Label> {
    sealed interface Intent

    sealed interface Label

    data class State(
        val charClassesList: ImmutableList<CharacterClassPresentationModel>,
    )
}