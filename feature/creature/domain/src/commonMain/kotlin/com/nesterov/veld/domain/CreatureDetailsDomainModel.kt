package com.nesterov.veld.domain

class CreatureDetailsDomainModel(
    val xpGain: Int,
    val hitPoints: Int,
    val proficiencyBonus: Int,
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
    val speed: SpeedDomainModel,
    val sense: SenseDomainModel,
    val spell: SpellDomainModel,
    val stats: StatsDomainModel,
    val armor: List<ArmorDomainModel>,
    val creatureActions: List<CreatureActionDomainModel>,
    val specialAbilities: List<CreatureActionDomainModel>,
    val legendaryActions: List<CreatureActionDomainModel>,
    val proficiencies: List<CreatureProficiencyDomainModel>,
)

class CreatureActionDomainModel(
    val name: String,
    val desc: String,
    val attackBonus: Int,
    val usage: UsageDomainModel,
    val actions: List<ActionDomainModel>,
    val damage: List<ActionDamageDomainModel>,
    val difficultyClass: DifficultyDomainModel,
)

class UsageDomainModel(
    val times: Int,
    val type: String,
    val restTypes: List<String>,
)

class DifficultyDomainModel(
    val difficultyValue: Int,
    val successType: String,
    val type: DifficultyTypeDomainModel,
)

class DifficultyTypeDomainModel(
    val name: String,
    val index: String,
)

class StatsDomainModel(
    val wisdom: Int,
    val charisma: Int,
    val strength: Int,
    val dexterity: Int,
    val constitution: Int,
    val intelligence: Int,
)

class ActionDamageDomainModel(
    val damageType: CreatureDamageTypeDomainModel,
    val damageDice: String,
)

class CreatureDamageTypeDomainModel(
    val index: String,
    val url: String,
    val name: String,
)

class ActionDomainModel(
    val count: Int,
    val name: String,
    val type: String,
)

class SpeedDomainModel(
    val burrow: String,
    val climb: String,
    val walk: String,
    val swim: String,
)

class SenseDomainModel(
    val passivePerception: Int,
    val tremorSense: String,
    val blindSight: String,
    val darkVision: String,
    val trueSight: String,
)

class ArmorDomainModel(
    val type: String,
    val value: Int,
)

class SpellDomainModel(
    val level: Int,
    val modifier: String,
    val magicSchool: String,
    val difficultyClass: String,
    val components: List<String>,
    val ability: AbilityDomainModel,
    val slots: CreatureSlotDomainModel,
    val spells: List<SpellOptionDomainModel>,
)

class AbilityDomainModel(
    val index: String,
    val url: String,
    val name: String,
)

class SpellOptionDomainModel(
    val index: String,
    val url: String,
    val name: String,
)

class CreatureSlotDomainModel(
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

class CreatureProficiencyDomainModel(
    val value: Int,
    val proficiency: ProficiencyDomainModel,
)

class ProficiencyDomainModel(
    val url: String,
    val index: String,
    val name: String,
)
