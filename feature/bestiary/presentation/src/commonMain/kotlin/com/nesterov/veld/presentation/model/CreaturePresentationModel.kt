package com.nesterov.veld.presentation.model

data class CreaturePresentationModel(
    val index: String,
    val name: String,
    val url: String,
    val status: CreatureMemoryStatus,
)

enum class CreatureMemoryStatus {
    LOCAL,
    REMOTE,
}