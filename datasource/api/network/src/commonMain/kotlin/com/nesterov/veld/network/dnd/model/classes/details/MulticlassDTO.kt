package com.nesterov.veld.network.dnd.model.classes.details

import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MulticlassDTO(
    @SerialName("prerequisites") val prerequisites: PrerequisitesDTO? = null,
    @SerialName("proficiencies") val proficiencies: List<ReferenceOptionDTO>? = null,
)

@Serializable
class PrerequisitesDTO(
    @SerialName("ability_score") val abilityScore: ReferenceOptionDTO? = null,
    @SerialName("minimum_score") val minimumScore: Int? = null,
)
