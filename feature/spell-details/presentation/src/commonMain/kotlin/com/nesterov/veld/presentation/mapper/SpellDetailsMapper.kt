package com.nesterov.veld.presentation.mapper

import com.nesterov.veld.domain.model.CharacterClassDomainModel
import com.nesterov.veld.domain.model.CharacterSubclassDomainModel
import com.nesterov.veld.domain.model.SpellDetailsDomainModel
import com.nesterov.veld.presentation.model.SpellDetailsPresentationModel
import com.nesterov.veld.presentation.model.utils.StatType
import com.nesterov.veld.presentation.model.utils.getAreaByContract
import com.nesterov.veld.presentation.model.utils.getMagicSchoolByContract
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap

fun SpellDetailsDomainModel.toSpellPresentationModel(): SpellDetailsPresentationModel =
    SpellDetailsPresentationModel(
        areaSize = areaSize,
        name = name,
        level = level,
        url = url,
        index = index,
        range = range,
        classIndex = index,
        material = material,
        duration = duration,
        schoolUrl = schoolUrl,
        attackType = attackType,
        higherLevel = higherLevel,
        description = description,
        castingTime = castingTime,
        schoolIndex = schoolIndex,
        isRitual = isRitual,
        isConcentration = isConcentration,
        schoolType = schoolIndex.getMagicSchoolByContract(),
        areaTypeTitle = areaType,
        areaType = areaType.getAreaByContract(),
        components = components.toImmutableList(),
        statTypes = mapOf(
            StatType.DURATION to duration,
            StatType.RANGE to range,
            StatType.ATTACK_TYPE to attackType,
            StatType.CASTING to castingTime,
        ).filter { it.value.isNotEmpty() }.toImmutableMap(),
        subClasses = subClasses.map(CharacterSubclassDomainModel::toCharacterClassPresentationModel).toImmutableList(),
        charClasses = charClasses.map(CharacterClassDomainModel::toCharacterClassPresentationModel).toImmutableList(),
        damage = damage.toDamagePresentationModel(),
    )

