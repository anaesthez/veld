package com.nesterov.veld.network.dnd.model.classes.details

import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SpellCastDTO(
    @SerialName("level") val level: Int? = null,
    @SerialName("spellcasting_ability") val spellCasting: ReferenceOptionDTO? = null,
    @SerialName("info") val info: List<SpellCastInfoDTO>? = null,
    @SerialName("spells") val spellUrl: String? = null,
)

@Serializable
class SpellCastInfoDTO(
    @SerialName("name") val name: String? = null,
    @SerialName("desc") val desc: String? = null,
)