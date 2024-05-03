package com.nesterov.veld.presentation.model

data class ChoiceProficiencyPresentationModel(
    val description: String,
    val options: List<ProficiencyOptionPresentationModel>,
)