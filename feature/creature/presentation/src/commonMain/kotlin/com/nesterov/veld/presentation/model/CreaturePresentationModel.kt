package com.nesterov.veld.presentation.model

class CreaturePresentationModel(
    val xpGain: Int,
    val size: String,
    val type: String,
    val index: String,
    val level: String,
    val hitPoints: Int,
    val imageUrl: String,
    val subtype: String,
    val hitDice: String,
    val languages: String,
    val alignments: String,
    val hitPointsRoll: String,
    val proficiencyBonus: Int,
    val challengeRating: Float,
    val speed: SpeedPresentationModel,
    val sense: SensePresentationModel,
    val armor: ArmorPresentationModel,
    val spell: SpellPresentationModel,
    val stats: StatsPresentationModel,
    val description: List<String>,
    val creatureActions: List<CreatureActionPresentationModel>,
    val specialAbilities: List<CreatureActionPresentationModel>,
    val legendaryActions: List<CreatureActionPresentationModel>,
    val proficiencies: List<CreatureProficiencyPresentationModel>,
)

class CreatureActionPresentationModel(
    val name: String,
    val desc: String,
    val attackBonus: Int,
    val usage: UsagePresentationModel,
    val actions: List<ActionPresentationModel>,
    val damage: List<ActionDamagePresentationModel>,
    val difficultyClass: DifficultyPresentationModel,
)

class UsagePresentationModel(
    val times: Int,
    val type: String,
    val restTypes: List<String>,
)

class DifficultyPresentationModel(
    val difficultyValue: Int,
    val successType: String,
    val type: DifficultyTypePresentationModel,
)

class DifficultyTypePresentationModel(
    val name: String,
    val index: String,
)

class StatsPresentationModel(
    val wisdom: Int,
    val charisma: Int,
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
)

class ActionDamagePresentationModel(
    val damageType: DamageTypePresentationModel,
    val damageDice: String,
)

class DamageTypePresentationModel(
    val index: String,
    val url: String,
    val name: String,
)

class ActionPresentationModel(
    val count: Int,
    val name: String,
    val type: String,
)

class SpeedPresentationModel(
    val burrow: String,
    val climb: String,
    val walk: String,
    val swim: String,
)

class SensePresentationModel(
    val passivePerception: Int,
    val tremorSense: String,
    val blindSight: String,
    val darkVision: String,
    val trueSight: String,
)

class ArmorPresentationModel(
    val type: String,
    val value: Int,
)

class SpellPresentationModel(
    val level: Int,
    val modifier: String,
    val magicSchool: String,
    val difficultyClass: String,
    val components: List<String>,
    val ability: AbilityPresentationModel,
    val slots: DamageSlotPresentationModel,
    val spells: List<SpellOptionPresentationModel>,
)

class AbilityPresentationModel(
    val index: String,
    val url: String,
    val name: String,
)

class SpellOptionPresentationModel(
    val index: String,
    val url: String,
    val name: String,
)

class DamageSlotPresentationModel(
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

class CreatureProficiencyPresentationModel(
    val value: Int,
    val proficiency: ProficiencyPresentationModel,
)

class ProficiencyPresentationModel(
    val url: String,
    val index: String,
    val name: String,
)