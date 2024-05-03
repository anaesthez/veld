package com.nesterov.veld.presentation.model

data class CharacterClassDetailsPresentationModel(
    val hitDie: HitDiceType,
    val charClassName: String,
    val multiClass: MultiClassPresentationModel,
    val spellCast: SpellCastPresentationModel,
    val choiceProficiencies: List<ChoiceProficiencyPresentationModel>,
    val commonProficiencies: List<ProficiencyPresentationModel>,
)