package com.nesterov.veld.network.dnd.model.creature

import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import com.nesterov.veld.network.dnd.model.spell.details.DamageSlotDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CreatureDTO(
    @SerialName("xp") val xpGain: Int? = null,
    @SerialName("size") val size: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("index") val index: String? = null,
    @SerialName("level") val level: String? = null,
    @SerialName("image") val imageUrl: String? = null,
    @SerialName("subtype") val subtype: String? = null,
    @SerialName("hit_dice") val hitDice: String? = null,
    @SerialName("hit_points") val hitPoints: Int? = null,
    @SerialName("languages") val languages: String? = null,
    @SerialName("alignments") val alignments: String? = null,
    @SerialName("hit_points_roll") val hitPointsRoll: String? = null,
    @SerialName("proficiency_bonus") val proficiencyBonus: Int? = null,
    @SerialName("challenge_rating") val challengeRating: Float? = null,
    @SerialName("speed") val speed: CreatureSpeedDTO? = null,
    @SerialName("senses") val senses: CreatureSensesDTO? = null,
    @SerialName("armor_class") val armorClass: ArmorClassDTO? = null,
    @SerialName("spellcasting") val spellCasting: CreatureSpellCastDTO? = null,
    @SerialName("desc") val description: List<String>? = null,
    @SerialName("actions") val actions: List<CreatureActionDTO>? = null,
    @SerialName("proficiencies") val proficiencies: List<CreatureProficiencyDTO>? = null,
    @SerialName("special_abilities") val specialAbilities: List<CreatureActionDTO>? = null,
    @SerialName("legendary_actions") val legendaryActions: List<CreatureActionDTO>? = null,
    //Stats
    @SerialName("wisdom") val wisdom: Int? = null,
    @SerialName("charisma") val charisma: Int? = null,
    @SerialName("strength") val strength: Int? = null,
    @SerialName("dexterity") val dexterity: Int? = null,
    @SerialName("constitution") val constitution: Int? = null,
    @SerialName("intelligence") val intelligence: Int? = null,
)

@Serializable
class CreatureSpellCastDTO(
    @SerialName("level") val level: Int? = null,
    @SerialName("ability") val ability: ReferenceOptionDTO? = null,
    @SerialName("dc") val difficultyClass: String? = null,
    @SerialName("modifier") val modifier: String? = null,
    @SerialName("components_required") val components: List<String>? = null,
    @SerialName("school") val magicSchool: String? = null,
    @SerialName("slots") val slots: DamageSlotDTO? = null,
    @SerialName("spells") val spells: List<ReferenceOptionDTO>? = null,
)

@Serializable
class CreatureProficiencyDTO(
    @SerialName("value") val value: Int? = null,
    @SerialName("proficiency") val proficiency: ReferenceOptionDTO? = null,
)

@Serializable
class CreatureSensesDTO(
    @SerialName("passive_perception") val passivePerception: Int? = null,
    @SerialName("blindsight") val blindSight: String? = null,
    @SerialName("darkvision") val darkVision: String? = null,
    @SerialName("tremorsense") val tremorSense: String? = null,
    @SerialName("truesight") val trueSight: String? = null,
)

@Serializable
class CreatureActionDTO(
    @SerialName("name") val name: String? = null,
    @SerialName("desc") val desc: String? = null,
    @SerialName("attack_bonus") val attackBonus: Int? = null,
    @SerialName("usage") val usage: ActionUsageDTO? = null,
    @SerialName("damage") val damage: List<ActionDamageDTO>? = null,
    @SerialName("dc") val difficultyClass: DifficultyClassDTO? = null,
    @SerialName("actions") val actions: List<ActionDTO>? = null,
)

@Serializable
class ActionUsageDTO(
    @SerialName("type") val type: String? = null,
    @SerialName("times") val times: Int? = null,
    @SerialName("rest_types") val restTypes: List<String>? = null,
)

@Serializable
class ActionDamageDTO(
    @SerialName("damage_type") val damageType: ReferenceOptionDTO? = null,
    @SerialName("damage_dice") val damageDice: String? = null,
)

@Serializable
class DifficultyClassDTO(
    @SerialName("dc_type") val type: DifficultyDTO? = null,
    @SerialName("dc_value") val difficultyValue: Int? = null,
    @SerialName("success_type") val successType: String? = null,
)

@Serializable
class DifficultyDTO(
    @SerialName("index") val index: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("url") val difficultyUrl: String? = null,
)

@Serializable
class ActionDTO(
    @SerialName("action_name") val name: String? = null,
    @SerialName("desc") val count: Int? = null,
    @SerialName("type") val type: String? = null,
)

@Serializable
class CreatureSpeedDTO(
    @SerialName("walk") val walk: String? = null,
    @SerialName("burrow") val burrow: String? = null,
    @SerialName("climb") val climb: String? = null,
    @SerialName("swim") val swim: String? = null,
)

@Serializable
class ArmorClassDTO(
    @SerialName("type") val type: String? = null,
    @SerialName("value") val value: Int? = null,
)