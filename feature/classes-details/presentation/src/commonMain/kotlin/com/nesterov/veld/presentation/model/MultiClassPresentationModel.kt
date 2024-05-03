package com.nesterov.veld.presentation.model

data class MultiClassPresentationModel(
    val prerequisites: List<PrerequisitePresentationModel>,
    val proficiencies: List<ProficiencyMultiClassPresentationModel>,
)