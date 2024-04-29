package ru.nesterov.veld.hub.store

import com.arkivanov.mvikotlin.core.store.Store
import ru.nesterov.veld.hub.model.SelectablePageUiModel
import kotlinx.collections.immutable.ImmutableList

interface HubStore : Store<HubStore.Intent, HubStore.State, HubStore.Label> {

    sealed interface Intent {
        data class SelectPage(val index: Int): Intent
        data class InputQuery(val input: String): Intent
    }

    sealed interface Label

    data class State(
        val pages: ImmutableList<SelectablePageUiModel>,
        val query: String,
    )
}