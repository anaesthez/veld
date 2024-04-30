package com.nesterov.veld.presentation.model

data class CharacterClassDetailsPresentationModel(
    val hitDie: Int,
    val charClassName: String,
    val proficiencyDescription: String,
    val multiClass: MultiClassPresentationModel,
    val spellCast: SpellCastPresentationModel,
    val choiceProficiencies: List<ChoiceProficiencyPresentationModel>,
    val commonProficiencies: List<ProficiencyPresentationModel>,
)