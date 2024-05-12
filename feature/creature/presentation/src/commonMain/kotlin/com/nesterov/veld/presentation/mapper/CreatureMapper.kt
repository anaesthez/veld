package com.nesterov.veld.presentation.mapper

import com.nesterov.veld.domain.AbilityDomainModel
import com.nesterov.veld.domain.ActionDamageDomainModel
import com.nesterov.veld.domain.ActionDomainModel
import com.nesterov.veld.domain.ArmorDomainModel
import com.nesterov.veld.domain.CreatureActionDomainModel
import com.nesterov.veld.domain.CreatureDomainModel
import com.nesterov.veld.domain.CreatureProficiencyDomainModel
import com.nesterov.veld.domain.DamageSlotDomainModel
import com.nesterov.veld.domain.DamageTypeDomainModel
import com.nesterov.veld.domain.DifficultyDomainModel
import com.nesterov.veld.domain.DifficultyTypeDomainModel
import com.nesterov.veld.domain.ProficiencyDomainModel
import com.nesterov.veld.domain.SenseDomainModel
import com.nesterov.veld.domain.SpeedDomainModel
import com.nesterov.veld.domain.SpellDomainModel
import com.nesterov.veld.domain.SpellOptionDomainModel
import com.nesterov.veld.domain.StatsDomainModel
import com.nesterov.veld.domain.UsageDomainModel
import com.nesterov.veld.presentation.model.AbilityPresentationModel
import com.nesterov.veld.presentation.model.ActionDamagePresentationModel
import com.nesterov.veld.presentation.model.ActionPresentationModel
import com.nesterov.veld.presentation.model.ArmorPresentationModel
import com.nesterov.veld.presentation.model.CreatureActionPresentationModel
import com.nesterov.veld.presentation.model.CreaturePresentationModel
import com.nesterov.veld.presentation.model.CreatureProficiencyPresentationModel
import com.nesterov.veld.presentation.model.DamageSlotPresentationModel
import com.nesterov.veld.presentation.model.DamageTypePresentationModel
import com.nesterov.veld.presentation.model.DifficultyPresentationModel
import com.nesterov.veld.presentation.model.DifficultyTypePresentationModel
import com.nesterov.veld.presentation.model.ProficiencyPresentationModel
import com.nesterov.veld.presentation.model.SensePresentationModel
import com.nesterov.veld.presentation.model.SpeedPresentationModel
import com.nesterov.veld.presentation.model.SpellOptionPresentationModel
import com.nesterov.veld.presentation.model.SpellPresentationModel
import com.nesterov.veld.presentation.model.StatsPresentationModel
import com.nesterov.veld.presentation.model.UsagePresentationModel

fun CreatureDomainModel.toCreaturePresentationModel(): CreaturePresentationModel =
    CreaturePresentationModel(
        xpGain = xpGain,
        size = size,
        type = type,
        index = index,
        level = level,
        hitPoints = hitPoints,
        imageUrl = imageUrl,
        subtype = subtype,
        hitDice = hitDice,
        languages = languages,
        alignments = alignments,
        hitPointsRoll = hitPointsRoll,
        proficiencyBonus = proficiencyBonus,
        challengeRating = challengeRating,
        speed = speed.toSpeedPresentationModel(),
        sense = sense.toSensePresentationModel(),
        armor = armor.toArmorPresentationModel(),
        spell = spell.toSpellPresentationModel(),
        stats = stats.toStatsPresentationModel(),
        description = description,
        creatureActions = creatureActions.map(CreatureActionDomainModel::toCreatureActionPresentationModel),
        specialAbilities = specialAbilities.map(CreatureActionDomainModel::toCreatureActionPresentationModel),
        legendaryActions = legendaryActions.map(CreatureActionDomainModel::toCreatureActionPresentationModel),
        proficiencies = proficiencies.map(CreatureProficiencyDomainModel::toCreatureProficiencyPresentationModel),
    )

fun StatsDomainModel.toStatsPresentationModel(): StatsPresentationModel =
    StatsPresentationModel(
        wisdom = wisdom,
        charisma = charisma,
        strength = strength,
        dexterity = dexterity,
        constitution = constitution,
        intelligence = intelligence
    )

fun SpeedDomainModel.toSpeedPresentationModel(): SpeedPresentationModel =
    SpeedPresentationModel(
        burrow = burrow,
        climb = climb,
        walk = walk,
        swim = swim
    )

fun SenseDomainModel.toSensePresentationModel(): SensePresentationModel =
    SensePresentationModel(
        passivePerception = passivePerception,
        tremorSense = tremorSense,
        blindSight = blindSight,
        darkVision = darkVision,
        trueSight = trueSight
    )

fun ArmorDomainModel.toArmorPresentationModel(): ArmorPresentationModel =
    ArmorPresentationModel(
        type = type,
        value = value
    )

fun SpellDomainModel.toSpellPresentationModel(): SpellPresentationModel =
    SpellPresentationModel(
        level = level,
        modifier = modifier,
        magicSchool = magicSchool,
        difficultyClass = difficultyClass,
        components = components,
        ability = ability.toAbilityPresentationModel(),
        slots = slots.toDamageSlotPresentationModel(),
        spells = spells.map(SpellOptionDomainModel::toSpellOptionPresentationModel)
    )

fun SpellOptionDomainModel.toSpellOptionPresentationModel(): SpellOptionPresentationModel =
    SpellOptionPresentationModel(
        index = index,
        url = url,
        name = name,
    )

fun AbilityDomainModel.toAbilityPresentationModel(): AbilityPresentationModel =
    AbilityPresentationModel(
        index = index,
        url = url,
        name = name
    )

fun DamageSlotDomainModel.toDamageSlotPresentationModel(): DamageSlotPresentationModel =
    DamageSlotPresentationModel(
        first = first,
        second = second,
        third = third,
        fourth = fourth,
        fifth = fifth,
        sixth = sixth,
        seventh = seventh,
        eighth = eighth,
        ninth = ninth
    )

fun CreatureActionDomainModel.toCreatureActionPresentationModel(): CreatureActionPresentationModel =
    CreatureActionPresentationModel(
        name = name,
        desc = desc,
        attackBonus = attackBonus,
        usage = usage.toUsagePresentationModel(),
        actions = actions.map(ActionDomainModel::toActionPresentationModel),
        damage = damage.map(ActionDamageDomainModel::toActionDamagePresentationModel),
        difficultyClass = difficultyClass.toDifficultyPresentationModel()
    )

fun UsageDomainModel.toUsagePresentationModel(): UsagePresentationModel =
    UsagePresentationModel(
        times = times,
        type = type,
        restTypes = restTypes
    )

fun DifficultyDomainModel.toDifficultyPresentationModel(): DifficultyPresentationModel =
    DifficultyPresentationModel(
        difficultyValue = difficultyValue,
        successType = successType,
        type = type.toDifficultyTypePresentationModel()
    )

fun DifficultyTypeDomainModel.toDifficultyTypePresentationModel(): DifficultyTypePresentationModel =
    DifficultyTypePresentationModel(
        name = name,
        index = index
    )

fun ActionDamageDomainModel.toActionDamagePresentationModel(): ActionDamagePresentationModel =
    ActionDamagePresentationModel(
        damageType = damageType.toDamageTypePresentationModel(),
        damageDice = damageDice
    )

fun DamageTypeDomainModel.toDamageTypePresentationModel(): DamageTypePresentationModel =
    DamageTypePresentationModel(
        index = index,
        url = url,
        name = name
    )

fun ActionDomainModel.toActionPresentationModel(): ActionPresentationModel =
    ActionPresentationModel(
        count = count,
        name = name,
        type = type
    )

fun CreatureProficiencyDomainModel.toCreatureProficiencyPresentationModel(): CreatureProficiencyPresentationModel =
    CreatureProficiencyPresentationModel(
        value = value,
        proficiency = proficiency.toProficiencyPresentationModel()
    )

fun ProficiencyDomainModel.toProficiencyPresentationModel(): ProficiencyPresentationModel =
    ProficiencyPresentationModel(
        url = url,
        index = index,
        name = name
    )

