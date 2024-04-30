package com.nesterov.veld.presentation.mapper

import com.nesterov.veld.domain.model.CharacterClassDetailsDomainModel
import com.nesterov.veld.domain.model.ChoiceProficiencyDomainModel
import com.nesterov.veld.domain.model.MultiClassDomainModel
import com.nesterov.veld.domain.model.ProficiencyDomainModel
import com.nesterov.veld.domain.model.SpellCastDomainModel
import com.nesterov.veld.domain.model.SpellCastInfoDomainModel
import com.nesterov.veld.presentation.model.CharacterClassDetailsPresentationModel
import com.nesterov.veld.presentation.model.ChoiceProficiencyPresentationModel
import com.nesterov.veld.presentation.model.MultiClassPresentationModel
import com.nesterov.veld.presentation.model.ProficiencyPresentationModel
import com.nesterov.veld.presentation.model.SpellCastInfoPresentationModel
import com.nesterov.veld.presentation.model.SpellCastPresentationModel

fun CharacterClassDetailsDomainModel.toCharacterClassDetailsPresentationModel(): CharacterClassDetailsPresentationModel {
    return CharacterClassDetailsPresentationModel(
        hitDie = hitDie,
        charClassName = charClassName,
        proficiencyDescription = proficiencyDescription,
        multiClass = multiClass.toMultiClassPresentationModel(),
        spellCast = spellCast.toSpellCastPresentationModel(),
        choiceProficiencies = choiceProficiencies.map(ChoiceProficiencyDomainModel::toChoiceProficiencyPresentationModel),
        commonProficiencies = commonProficiencies.map(ProficiencyDomainModel::toChoiceProficiencyPresentationModel)
    )
}

fun MultiClassDomainModel.toMultiClassPresentationModel(): MultiClassPresentationModel =
    MultiClassPresentationModel(
        abilityName = abilityName,
        abilityIndex = abilityIndex,
        minimumScore = minimumScore
    )

fun SpellCastDomainModel.toSpellCastPresentationModel(): SpellCastPresentationModel {
    return SpellCastPresentationModel(
        level = level,
        spellAbilityTitle = spellAbilityTitle,
        info = info.map(SpellCastInfoDomainModel::toSpellCastInfoPresentationModel),
        charSpellsUrl = charSpellsUrl
    )
}

fun SpellCastInfoDomainModel.toSpellCastInfoPresentationModel(): SpellCastInfoPresentationModel {
    return SpellCastInfoPresentationModel(
        title = title,
        description = description
    )
}

fun ChoiceProficiencyDomainModel.toChoiceProficiencyPresentationModel(): ChoiceProficiencyPresentationModel {
    return ChoiceProficiencyPresentationModel(
        title = title,
        index = index
    )
}

fun ProficiencyDomainModel.toChoiceProficiencyPresentationModel(): ProficiencyPresentationModel {
    return ProficiencyPresentationModel(
        title = title,
        index = index
    )
}


