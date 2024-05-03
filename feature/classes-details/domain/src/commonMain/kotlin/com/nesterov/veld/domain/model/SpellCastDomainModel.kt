package com.nesterov.veld.domain.model

class SpellCastDomainModel(
    val level: Int,
    val spellAbilityTitle: String,
    val info: List<SpellCastInfoDomainModel>,
    val charSpellsUrl: String,
)