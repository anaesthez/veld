package com.nesterov.veld.domain.model

data class CharacterClassDetailsDomainModel(
    val hitDie: Int,
    val charClassName: String,
    val proficiencyDescription: String,
    val multiClass: MultiClassDomainModel,
    val spellCast: SpellCastDomainModel,
    val choiceProficiencies: List<ChoiceProficiencyDomainModel>,
    val commonProficiencies: List<ProficiencyDomainModel>,
)