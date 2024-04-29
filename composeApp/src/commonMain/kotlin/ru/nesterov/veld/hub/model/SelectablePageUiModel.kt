package ru.nesterov.veld.hub.model

import androidx.compose.runtime.Immutable
import ru.nesterov.veld.hub.model.Page

@Immutable
data class SelectablePageUiModel(
    val pageType: Page,
    val isSelected: Boolean,
)