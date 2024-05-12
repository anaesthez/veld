package com.nesterov.veld.data

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
import com.nesterov.veld.helpers.orZero
import com.nesterov.veld.network.dnd.model.creature.ActionDTO
import com.nesterov.veld.network.dnd.model.creature.ActionDamageDTO
import com.nesterov.veld.network.dnd.model.creature.ActionUsageDTO
import com.nesterov.veld.network.dnd.model.creature.ArmorClassDTO
import com.nesterov.veld.network.dnd.model.creature.CreatureActionDTO
import com.nesterov.veld.network.dnd.model.creature.CreatureDTO
import com.nesterov.veld.network.dnd.model.creature.CreatureProficiencyDTO
import com.nesterov.veld.network.dnd.model.creature.CreatureSensesDTO
import com.nesterov.veld.network.dnd.model.creature.CreatureSpeedDTO
import com.nesterov.veld.network.dnd.model.creature.CreatureSpellCastDTO
import com.nesterov.veld.network.dnd.model.creature.DifficultyClassDTO
import com.nesterov.veld.network.dnd.model.creature.DifficultyDTO
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import com.nesterov.veld.network.dnd.model.spell.details.DamageSlotDTO

fun CreatureDTO.toCreatureDomainModel(): CreatureDetailsDomainModel =
    CreatureDetailsDomainModel(
        xpGain = xpGain.orZero(),
        size = size.orEmpty(),
        type = type.orEmpty(),
        index = index.orEmpty(),
        level = level.orEmpty(),
        hitPoints = hitPoints.orZero(),
        imageUrl = imageUrl.orEmpty(),
        subtype = subtype.orEmpty(),
        hitDice = hitDice.orEmpty(),
        languages = languages.orEmpty(),
        alignments = alignments.orEmpty(),
        hitPointsRoll = hitPointsRoll.orEmpty(),
        proficiencyBonus = proficiencyBonus.orZero(),
        challengeRating = challengeRating.orZero(),
        speed = speed?.toSpeedDomainModel() ?: SpeedDomainModel("", "", "", ""),
        sense = senses?.toSenseDomainModel() ?: SenseDomainModel(0, "", "", "", ""),
        spell = spellCasting?.toSpellDomainModel() ?: SpellDomainModel(
            0,
            "",
            "",
            "",
            emptyList(),
            AbilityDomainModel("", "", ""),
            CreatureSlotDomainModel("", "", "", "", "", "", "", "", ""),
            emptyList()
        ),
        stats = StatsDomainModel(
            wisdom = wisdom.orZero(),
            charisma = charisma.orZero(),
            strength = strength.orZero(),
            dexterity = dexterity.orZero(),
            constitution = constitution.orZero(),
            intelligence = intelligence.orZero()
        ),
        description = description.orEmpty(),
        armor = armorClass?.map(ArmorClassDTO::toArmorDomainModel).orEmpty(),
        creatureActions = actions?.map { it.toCreatureActionDomainModel() }.orEmpty(),
        specialAbilities = specialAbilities?.map { it.toCreatureActionDomainModel() }.orEmpty(),
        legendaryActions = legendaryActions?.map { it.toCreatureActionDomainModel() }.orEmpty(),
        proficiencies = proficiencies?.map { it.toCreatureProficiencyDomainModel() }.orEmpty()
    )

fun CreatureSpeedDTO.toSpeedDomainModel(): SpeedDomainModel =
    SpeedDomainModel(
        burrow = burrow.orEmpty(),
        climb = climb.orEmpty(),
        walk = walk.orEmpty(),
        swim = swim.orEmpty()
    )

fun CreatureSensesDTO.toSenseDomainModel(): SenseDomainModel =
    SenseDomainModel(
        passivePerception = passivePerception.orZero(),
        tremorSense = tremorSense.orEmpty(),
        blindSight = blindSight.orEmpty(),
        darkVision = darkVision.orEmpty(),
        trueSight = trueSight.orEmpty()
    )

fun ArmorClassDTO.toArmorDomainModel(): ArmorDomainModel =
    ArmorDomainModel(
        type = type.orEmpty(),
        value = value.orZero()
    )

fun CreatureSpellCastDTO.toSpellDomainModel(): SpellDomainModel =
    SpellDomainModel(
        level = level.orZero(),
        modifier = modifier.orEmpty(),
        magicSchool = magicSchool.orEmpty(),
        difficultyClass = difficultyClass.orEmpty(),
        components = components.orEmpty(),
        ability = ability?.toAbilityDomainModel() ?: AbilityDomainModel("", "", ""),
        slots = slots?.toDamageSlotDomainModel() ?: CreatureSlotDomainModel(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        ),
        spells = spells?.map { it.toSpellOptionDomainModel() } ?: emptyList()
    )

fun CreatureActionDTO.toCreatureActionDomainModel(): CreatureActionDomainModel =
    CreatureActionDomainModel(
        name = name.orEmpty(),
        desc = desc.orEmpty(),
        attackBonus = attackBonus.orZero(),
        usage = usage?.toUsageDomainModel() ?: UsageDomainModel(0, "", emptyList()),
        actions = actions?.map { it.toActionDomainModel() }.orEmpty(),
        damage = damage?.map { it.toActionDamageDomainModel() }.orEmpty(),
        difficultyClass = difficultyClass?.toDifficultyDomainModel() ?: DifficultyDomainModel(
            0,
            "",
            DifficultyTypeDomainModel("", "")
        )
    )

fun ActionUsageDTO.toUsageDomainModel(): UsageDomainModel =
    UsageDomainModel(
        times = times.orZero(),
        type = type.orEmpty(),
        restTypes = restTypes.orEmpty()
    )

fun DifficultyClassDTO.toDifficultyDomainModel(): DifficultyDomainModel =
    DifficultyDomainModel(
        difficultyValue = difficultyValue.orZero(),
        successType = successType.orEmpty(),
        type = type?.toDifficultyTypeDomainModel() ?: DifficultyTypeDomainModel("", "")
    )

fun DifficultyDTO.toDifficultyTypeDomainModel(): DifficultyTypeDomainModel =
    DifficultyTypeDomainModel(
        name = name.orEmpty(),
        index = index.orEmpty()
    )

fun ActionDamageDTO.toActionDamageDomainModel(): ActionDamageDomainModel =
    ActionDamageDomainModel(
        damageType = damageType?.toDamageTypeDomainModel() ?: CreatureDamageTypeDomainModel(
            "",
            "",
            ""
        ),
        damageDice = damageDice.orEmpty()
    )

fun ReferenceOptionDTO.toDamageTypeDomainModel(): CreatureDamageTypeDomainModel =
    CreatureDamageTypeDomainModel(
        index = index.orEmpty(),
        url = url.orEmpty(),
        name = name.orEmpty()
    )

fun ActionDTO.toActionDomainModel(): ActionDomainModel =
    ActionDomainModel(
        count = count.orZero(),
        name = name.orEmpty(),
        type = type.orEmpty()
    )

fun CreatureProficiencyDTO.toCreatureProficiencyDomainModel(): CreatureProficiencyDomainModel =
    CreatureProficiencyDomainModel(
        value = value.orZero(),
        proficiency = proficiency?.toProficiencyDomainModel() ?: ProficiencyDomainModel("", "", "")
    )

fun ReferenceOptionDTO.toProficiencyDomainModel(): ProficiencyDomainModel =
    ProficiencyDomainModel(
        url = url.orEmpty(),
        index = index.orEmpty(),
        name = name.orEmpty()
    )

fun ReferenceOptionDTO.toAbilityDomainModel(): AbilityDomainModel =
    AbilityDomainModel(
        index = index.orEmpty(),
        url = url.orEmpty(),
        name = name.orEmpty()
    )

fun ReferenceOptionDTO.toSpellOptionDomainModel(): SpellOptionDomainModel =
    SpellOptionDomainModel(
        index = index.orEmpty(),
        url = url.orEmpty(),
        name = name.orEmpty()
    )

fun DamageSlotDTO.toDamageSlotDomainModel(): CreatureSlotDomainModel =
    CreatureSlotDomainModel(
        first = first.orEmpty(),
        second = second.orEmpty(),
        third = third.orEmpty(),
        fourth = fourth.orEmpty(),
        fifth = fifth.orEmpty(),
        sixth = sixth.orEmpty(),
        seventh = seventh.orEmpty(),
        eighth = eighth.orEmpty(),
        ninth = ninth.orEmpty()
    )