package com.nesterov.veld.network.dnd.model.spell.details

import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SpellDetailsDTO(
    @SerialName("index") val index: String? = null,
    @SerialName("higher_level") val higherLevel: List<String>? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("desc") val description: List<String>? = null,
    @SerialName("range") val range: String? = null,
    @SerialName("material") val material: String? = null,
    @SerialName("duration") val duration: String? = null,
    @SerialName("casting_time") val castingTime: String? = null,
    @SerialName("attack_type") val attackType: String? = null,
    @SerialName("level") val level: Int? = null,
    @SerialName("ritual") val isRitual: Boolean? = null,
    @SerialName("concentration") val isConcentration: Boolean? = null,
    @SerialName("components") val components: List<String>? = null,
    @SerialName("damage") val damage: DamageDTO? = null,
    @SerialName("area_of_effect") val effectArea: EffectAreaDTO? = null,
    @SerialName("school") val school: ReferenceOptionDTO? = null,
    @SerialName("classes") val charClasses: List<ReferenceOptionDTO?>? = null,
    @SerialName("subclasses") val subclasses: List<ReferenceOptionDTO?>? = null,
)
