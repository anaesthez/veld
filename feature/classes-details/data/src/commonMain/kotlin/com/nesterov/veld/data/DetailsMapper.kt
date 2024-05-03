package com.nesterov.veld.data

import com.nesterov.veld.domain.model.CharacterClassDetailsDomainModel
import com.nesterov.veld.domain.model.ChoiceProficiencyDomainModel
import com.nesterov.veld.domain.model.MultiClassDomainModel
import com.nesterov.veld.domain.model.PrerequisiteDomainModel
import com.nesterov.veld.domain.model.ProficiencyDomainModel
import com.nesterov.veld.domain.model.ProficiencyMultiClassDomainModel
import com.nesterov.veld.domain.model.ProficiencyOptionDomainModel
import com.nesterov.veld.domain.model.SpellCastDomainModel
import com.nesterov.veld.domain.model.SpellCastInfoDomainModel
import com.nesterov.veld.helpers.orZero
import com.nesterov.veld.network.dnd.model.classes.details.ClassDetailsDTO
import com.nesterov.veld.network.dnd.model.classes.details.MulticlassDTO
import com.nesterov.veld.network.dnd.model.classes.details.OptionDTO
import com.nesterov.veld.network.dnd.model.classes.details.PrerequisitesDTO
import com.nesterov.veld.network.dnd.model.classes.details.ProficiencyChoicesDTO
import com.nesterov.veld.network.dnd.model.classes.details.SpellCastDTO
import com.nesterov.veld.network.dnd.model.classes.details.SpellCastInfoDTO
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO

fun ClassDetailsDTO.toCharacterClassDetailsDomainModel(): CharacterClassDetailsDomainModel =
    CharacterClassDetailsDomainModel(
        hitDie = hitDie.orZero(),
        charClassName = name.orEmpty(),
        choiceProficiencies = proficiencyChoices?.map(ProficiencyChoicesDTO::toChoiceProficiencyDomainModel)
            .orEmpty(),
        commonProficiencies = proficiencies?.map(ReferenceOptionDTO::toProficiencyDomainModel)
            .orEmpty(),
        multiClass = multiClassing?.toMultiClassDomainModel() ?: MultiClassDomainModel(
            emptyList(),
            emptyList()
        ),
        spellCast = spellCasting?.toSpellCastDomainModel() ?: SpellCastDomainModel(
            0,
            "",
            emptyList(),
            ""
        )
    )

fun ProficiencyChoicesDTO.toChoiceProficiencyDomainModel(): ChoiceProficiencyDomainModel =
    ChoiceProficiencyDomainModel(
        description = desc.orEmpty(),
        options = from?.options?.map(OptionDTO::toProficiencyOptionDomainMode).orEmpty(),
    )

fun OptionDTO.toProficiencyOptionDomainMode(): ProficiencyOptionDomainModel =
    ProficiencyOptionDomainModel(
        index = item?.index.orEmpty(),
        title = item?.name ?: choice?.description.orEmpty(),
    )

fun ReferenceOptionDTO.toProficiencyDomainModel(): ProficiencyDomainModel =
    ProficiencyDomainModel(
        index = index.orEmpty(),
        title = name.orEmpty(),
    )

fun MulticlassDTO.toMultiClassDomainModel(): MultiClassDomainModel =
    MultiClassDomainModel(
        prerequisites = prerequisites?.map(PrerequisitesDTO::toPrerequisitesMultiClassDomainModel)
            .orEmpty(),
        proficiencies = proficiencies?.map(ReferenceOptionDTO::toProficiencyMultiClassDomainModel)
            .orEmpty(),
    )

fun PrerequisitesDTO.toPrerequisitesMultiClassDomainModel(): PrerequisiteDomainModel =
    PrerequisiteDomainModel(
        index = abilityScore?.index.orEmpty(),
        title = abilityScore?.name.orEmpty(),
        minimumScore = minimumScore.orZero(),
    )

fun ReferenceOptionDTO.toProficiencyMultiClassDomainModel(): ProficiencyMultiClassDomainModel =
    ProficiencyMultiClassDomainModel(
        index = index.orEmpty(),
        title = name.orEmpty(),
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
        description = desc?.joinToString().orEmpty(),
    )