package com.nesterov.veld.domain.model

data class MultiClassDomainModel(
    val prerequisites: List<PrerequisiteDomainModel>,
    val proficiencies: List<ProficiencyMultiClassDomainModel>,
)