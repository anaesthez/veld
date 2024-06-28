package com.nesterov.veld.presentation.creature.mapper

import com.nesterov.veld.domain.AbilityDomainModel
import com.nesterov.veld.domain.ActionDamageDomainModel
import com.nesterov.veld.domain.ActionDomainModel
import com.nesterov.veld.domain.ArmorDomainModel
import com.nesterov.veld.domain.CreatureActionDomainModel
import com.nesterov.veld.domain.CreatureDamageTypeDomainModel
import com.nesterov.veld.domain.CreatureDetailsDomainModel
import com.nesterov.veld.domain.CreatureProficiencyDomainModel
import com.nesterov.veld.domain.CreatureSlotDomainModel
import com.nesterov.veld.domain.DifficultyDomainModel
import com.nesterov.veld.domain.DifficultyTypeDomainModel
import com.nesterov.veld.domain.ProficiencyDomainModel
import com.nesterov.veld.domain.SenseDomainModel
import com.nesterov.veld.domain.SpeedDomainModel
import com.nesterov.veld.domain.SpellDomainModel
import com.nesterov.veld.domain.SpellOptionDomainModel
import com.nesterov.veld.domain.StatsDomainModel
import com.nesterov.veld.domain.UsageDomainModel
import com.nesterov.veld.presentation.creature.model.AbilityPresentationModel
import com.nesterov.veld.presentation.creature.model.ActionDamagePresentationModel
import com.nesterov.veld.presentation.creature.model.ActionPresentationModel
import com.nesterov.veld.presentation.creature.model.ActionType
import com.nesterov.veld.presentation.creature.model.ArmorPresentationModel
import com.nesterov.veld.presentation.creature.model.CreatureActionPresentationModel
import com.nesterov.veld.presentation.creature.model.CreatureDamageTypePresentationModel
import com.nesterov.veld.presentation.creature.model.CreatureDetailsPresentationModel
import com.nesterov.veld.presentation.creature.model.CreatureProficiencyPresentationModel
import com.nesterov.veld.presentation.creature.model.CreatureSlotPresentationModel
import com.nesterov.veld.presentation.creature.model.CreatureSpellPresentationModel
import com.nesterov.veld.presentation.creature.model.DifficultyPresentationModel
import com.nesterov.veld.presentation.creature.model.DifficultyTypePresentationModel
import com.nesterov.veld.presentation.creature.model.MovingType
import com.nesterov.veld.presentation.creature.model.ProficiencyReferencePresentationModel
import com.nesterov.veld.presentation.creature.model.SensePresentationModel
import com.nesterov.veld.presentation.creature.model.SpeedPresentationModel
import com.nesterov.veld.presentation.creature.model.SpellOptionPresentationModel
import com.nesterov.veld.presentation.creature.model.Stat
import com.nesterov.veld.presentation.creature.model.StatsPresentationModel
import com.nesterov.veld.presentation.creature.model.UsagePresentationModel
import kotlinx.collections.immutable.toImmutableMap

fun CreatureDetailsDomainModel.toCreaturePresentationModel(): CreatureDetailsPresentationModel =
    CreatureDetailsPresentationModel(
        xpGain = xpGain,
        size = size,
        type = type,
        name = name,
        index = index,
        level = level,
        hitPoints = hitPoints,
        imageUrl = imageUrl,
        subtype = subtype,
        hitDice = hitDice,
        description = description,
        languages = languages,
        alignments = alignments,
        hitPointsRoll = hitPointsRoll,
        proficiencyBonus = proficiencyBonus,
        challengeRating = challengeRating,
        speed = speed.toSpeedPresentationModel(),
        sense = sense.toSensePresentationModel(),
        spell = spell.toSpellPresentationModel(),
        stats = stats.toStatsPresentationModel(),
        armor = armor.map(ArmorDomainModel::toArmorPresentationModel),
        actionsMap = mapOf(
            ActionType.COMMON to creatureActions.map(CreatureActionDomainModel::toCreatureActionPresentationModel),
            ActionType.SPECIAL to specialAbilities.map(CreatureActionDomainModel::toCreatureActionPresentationModel),
            ActionType.LEGENDARY to legendaryActions.map(CreatureActionDomainModel::toCreatureActionPresentationModel),
        ),
        proficiencies = proficiencies.map(CreatureProficiencyDomainModel::toCreatureProficiencyPresentationModel),
    )

fun StatsDomainModel.toStatsPresentationModel(): StatsPresentationModel =
    StatsPresentationModel(
        statsMap = mapOf(
            Stat.CHARISMA to charisma,
            Stat.WISDOM to wisdom,
            Stat.STRENGTH to strength,
            Stat.DEXTERITY to dexterity,
            Stat.CONSTITUTION to constitution,
            Stat.INTELLIGENCE to intelligence,
        ).filter { it.value != 0 }.toImmutableMap(),
    )

fun SpeedDomainModel.toSpeedPresentationModel(): SpeedPresentationModel =
    SpeedPresentationModel(
        movementsMap = mapOf(
            MovingType.BURROW to burrow,
            MovingType.CLIMBING to climb,
            MovingType.SWIM to swim,
            MovingType.WALK to walk,
        ).filter { it.value.isNotBlank() }.toImmutableMap(),
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

fun SpellDomainModel.toSpellPresentationModel(): CreatureSpellPresentationModel =
    CreatureSpellPresentationModel(
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

fun CreatureSlotDomainModel.toDamageSlotPresentationModel(): CreatureSlotPresentationModel =
    CreatureSlotPresentationModel(
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

fun CreatureDamageTypeDomainModel.toDamageTypePresentationModel(): CreatureDamageTypePresentationModel =
    CreatureDamageTypePresentationModel(
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

fun ProficiencyDomainModel.toProficiencyPresentationModel(): ProficiencyReferencePresentationModel =
    ProficiencyReferencePresentationModel(
        url = url,
        index = index,
        name = name
    )

