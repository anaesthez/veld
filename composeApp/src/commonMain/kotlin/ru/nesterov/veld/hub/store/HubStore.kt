package ru.nesterov.veld.hub.store

import androidx.compose.runtime.Immutable
import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.collections.immutable.ImmutableList
import ru.nesterov.veld.hub.model.SelectablePageUiModel

interface HubStore : Store<HubStore.Intent, HubStore.State, HubStore.Label> {

    sealed interface Intent {
        data class SelectPage(val index: Int): Intent
        data class InputQuery(val input: String): Intent
    }

    sealed interface Label

    @Immutable
    data class State(
        val pages: ImmutableList<SelectablePageUiModel>,
        val query: String,
    )
}