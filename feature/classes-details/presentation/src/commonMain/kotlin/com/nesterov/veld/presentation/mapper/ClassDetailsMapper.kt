package com.nesterov.veld.presentation.mapper

import com.nesterov.veld.domain.model.CharacterClassDetailsDomainModel
import com.nesterov.veld.domain.model.ChoiceProficiencyDomainModel
import com.nesterov.veld.domain.model.MultiClassDomainModel
import com.nesterov.veld.domain.model.PrerequisiteDomainModel
import com.nesterov.veld.domain.model.ProficiencyDomainModel
import com.nesterov.veld.domain.model.ProficiencyMultiClassDomainModel
import com.nesterov.veld.domain.model.ProficiencyOptionDomainModel
import com.nesterov.veld.domain.model.SpellCastDomainModel
import com.nesterov.veld.domain.model.SpellCastInfoDomainModel
import com.nesterov.veld.presentation.model.CharacterClassDetailsPresentationModel
import com.nesterov.veld.presentation.model.ChoiceProficiencyPresentationModel
import com.nesterov.veld.presentation.model.HitDiceType
import com.nesterov.veld.presentation.model.MultiClassPresentationModel
import com.nesterov.veld.presentation.model.PrerequisitePresentationModel
import com.nesterov.veld.presentation.model.ProficiencyMultiClassPresentationModel
import com.nesterov.veld.presentation.model.ProficiencyOptionPresentationModel
import com.nesterov.veld.presentation.model.ProficiencyPresentationModel
import com.nesterov.veld.presentation.model.SpellCastInfoPresentationModel
import com.nesterov.veld.presentation.model.SpellCastPresentationModel

fun CharacterClassDetailsDomainModel.toCharacterClassDetailsPresentationModel(): CharacterClassDetailsPresentationModel {
    return CharacterClassDetailsPresentationModel(
        hitDie = when (hitDie) {
            6 -> HitDiceType.DICE_6
            8 -> HitDiceType.DICE_8
            10 -> HitDiceType.DICE_10
            12 -> HitDiceType.DICE_12
            else -> HitDiceType.EMPTY
        },
        charClassName = charClassName,
        multiClass = multiClass.toMultiClassPresentationModel(),
        spellCast = spellCast.toSpellCastPresentationModel(),
        choiceProficiencies = choiceProficiencies.map(ChoiceProficiencyDomainModel::toChoiceProficiencyPresentationModel),
        commonProficiencies = commonProficiencies.map(ProficiencyDomainModel::toChoiceProficiencyPresentationModel)
    )
}

fun MultiClassDomainModel.toMultiClassPresentationModel(): MultiClassPresentationModel =
    MultiClassPresentationModel(
        prerequisites = prerequisites.map(PrerequisiteDomainModel::toPrerequisitesMultiClassDomainModel),
        proficiencies = proficiencies.map(ProficiencyMultiClassDomainModel::toProficiencyMultiClassDomainModel),
    )

fun PrerequisiteDomainModel.toPrerequisitesMultiClassDomainModel(): PrerequisitePresentationModel =
    PrerequisitePresentationModel(
        index = index,
        title = title,
        minimumScore = minimumScore,
    )

fun ProficiencyMultiClassDomainModel.toProficiencyMultiClassDomainModel(): ProficiencyMultiClassPresentationModel =
    ProficiencyMultiClassPresentationModel(
        index = index,
        title = title,
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
        description = description,
        options = options.map(ProficiencyOptionDomainModel::toProficiencyOptionPresentationModel)
    )
}

fun ProficiencyOptionDomainModel.toProficiencyOptionPresentationModel(): ProficiencyOptionPresentationModel =
    ProficiencyOptionPresentationModel(
        index = index,
        title = title,
    )

fun ProficiencyDomainModel.toChoiceProficiencyPresentationModel(): ProficiencyPresentationModel {
    return ProficiencyPresentationModel(
        title = title,
        index = index
    )
}


