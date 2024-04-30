package com.nesterov.veld.data

import com.nesterov.veld.domain.model.CharacterClassDetailsDomainModel
import com.nesterov.veld.domain.model.ChoiceProficiencyDomainModel
import com.nesterov.veld.domain.model.MultiClassDomainModel
import com.nesterov.veld.domain.model.ProficiencyDomainModel
import com.nesterov.veld.domain.model.SpellCastDomainModel
import com.nesterov.veld.domain.model.SpellCastInfoDomainModel
import com.nesterov.veld.helpers.orZero
import com.nesterov.veld.network.dnd.model.classes.details.ClassDetailsDTO
import com.nesterov.veld.network.dnd.model.classes.details.MulticlassDTO
import com.nesterov.veld.network.dnd.model.classes.details.SpellCastDTO
import com.nesterov.veld.network.dnd.model.classes.details.SpellCastInfoDTO
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO

fun ClassDetailsDTO.toCharacterClassDetailsDomainModel(): CharacterClassDetailsDomainModel =
    CharacterClassDetailsDomainModel(
        hitDie = hitDie.orZero(),
        charClassName = name.orEmpty(),
        proficiencyDescription = proficiencyChoices?.desc.orEmpty(),
        choiceProficiencies = proficiencyChoices?.from?.options?.map { it.item.toChoiceProficiencyDomainModel() }
            .orEmpty(),
        commonProficiencies = proficiencies?.map(ReferenceOptionDTO::toProficiencyDomainModel)
            .orEmpty(),
        multiClass = multiClassing?.toMultiClassDomainModel() ?: MultiClassDomainModel("", "", 0),
        spellCast = spellCasting?.toSpellCastDomainModel() ?: SpellCastDomainModel(
            0,
            "",
            emptyList(),
            ""
        )
    )

fun ReferenceOptionDTO.toChoiceProficiencyDomainModel(): ChoiceProficiencyDomainModel =
    ChoiceProficiencyDomainModel(
        index = index.orEmpty(),
        title = name.orEmpty(),
    )

fun ReferenceOptionDTO.toProficiencyDomainModel(): ProficiencyDomainModel =
    ProficiencyDomainModel(
        index = index.orEmpty(),
        title = name.orEmpty(),
    )

fun MulticlassDTO.toMultiClassDomainModel(): MultiClassDomainModel =
    MultiClassDomainModel(
        abilityName = prerequisites?.abilityScore?.name.orEmpty(),
        abilityIndex = prerequisites?.abilityScore?.index.orEmpty(),
        minimumScore = prerequisites?.minimumScore.orZero()
    )

fun SpellCastDTO.toSpellCastDomainModel(): SpellCastDomainModel =
    SpellCastDomainModel(
        level = level.orZero(),
        info = info?.map(SpellCastInfoDTO::toSpellCastInfoDomainModel).orEmpty(),
        charSpellsUrl = spellUrl.orEmpty(),
        spellAbilityTitle = spellCasting?.name.orEmpty(),
    )

fun SpellCastInfoDTO.toSpellCastInfoDomainModel(): SpellCastInfoDomainModel =
    SpellCastInfoDomainModel(
        title = name.orEmpty(),
        description = desc.orEmpty(),
    )