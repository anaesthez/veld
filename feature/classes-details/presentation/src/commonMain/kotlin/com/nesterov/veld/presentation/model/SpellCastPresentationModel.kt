package com.nesterov.veld.presentation.model

class SpellCastPresentationModel(
    val level: Int,
    val spellAbilityTitle: String,
    val info: List<SpellCastInfoPresentationModel>,
    val charSpellsUrl: String,
)