package com.nesterov.veld.presentation.creature.model

import kotlinx.collections.immutable.ImmutableMap

data class CreatureDetailsPresentationModel(
    val xpGain: Int,
    val hitPoints: Int,
    val size: String,
    val type: String,
    val name: String,
    val index: String,
    val level: String,
    val imageUrl: String,
    val subtype: String,
    val hitDice: String,
    val languages: String,
    val alignments: String,
    val description: String,
    val hitPointsRoll: String,
    val challengeRating: Float,
    val proficiencyBonus: Int,
    val speed: SpeedPresentationModel,
    val sense: SensePresentationModel,
    val stats: StatsPresentationModel,
    val spell: CreatureSpellPresentationModel,
    val armor: List<ArmorPresentationModel>,
    val actionsMap: Map<ActionType, List<CreatureActionPresentationModel>>,
    val proficiencies: List<CreatureProficiencyPresentationModel>,
)

data class CreatureActionPresentationModel(
    val name: String,
    val desc: String,
    val attackBonus: Int,
    val usage: UsagePresentationModel,
    val actions: List<ActionPresentationModel>,
    val damage: List<ActionDamagePresentationModel>,
    val difficultyClass: DifficultyPresentationModel,
)

data class UsagePresentationModel(
    val times: Int,
    val type: String,
    val restTypes: List<String>,
)

data class DifficultyPresentationModel(
    val difficultyValue: Int,
    val successType: String,
    val type: DifficultyTypePresentationModel,
)

data class DifficultyTypePresentationModel(
    val name: String,
    val index: String,
)

data class StatsPresentationModel(
    val statsMap: ImmutableMap<Stat, Int>,
)

data class ActionDamagePresentationModel(
    val damageType: CreatureDamageTypePresentationModel,
    val damageDice: String,
)

data class CreatureDamageTypePresentationModel(
    val index: String,
    val url: String,
    val name: String,
)

data class ActionPresentationModel(
    val count: Int,
    val name: String,
    val type: String,
)

data class SpeedPresentationModel(
    val movementsMap: ImmutableMap<MovingType, String>,
)

data class SensePresentationModel(
    val passivePerception: Int,
    val tremorSense: String,
    val blindSight: String,
    val darkVision: String,
    val trueSight: String,
)

data class ArmorPresentationModel(
    val type: String,
    val value: Int,
)

data class CreatureSpellPresentationModel(
    val level: Int,
    val modifier: String,
    val magicSchool: String,
    val difficultyClass: String,
    val components: List<String>,
    val ability: AbilityPresentationModel,
    val slots: CreatureSlotPresentationModel,
    val spells: List<SpellOptionPresentationModel>,
)

data class AbilityPresentationModel(
    val index: String,
    val url: String,
    val name: String,
)

data class SpellOptionPresentationModel(
    val index: String,
    val url: String,
    val name: String,
)

data class CreatureSlotPresentationModel(
    val first: String,
    val second: String,
    val third: String,
    val fourth: String,
    val fifth: String,
    val sixth: String,
    val seventh: String,
    val eighth: String,
    val ninth: String,
)

data class CreatureProficiencyPresentationModel(
    val value: Int,
    val proficiency: ProficiencyReferencePresentationModel,
)

data class ProficiencyReferencePresentationModel(
    val url: String,
    val index: String,
    val name: String,
)