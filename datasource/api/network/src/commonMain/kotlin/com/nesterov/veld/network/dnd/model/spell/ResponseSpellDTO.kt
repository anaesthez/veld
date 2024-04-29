package com.nesterov.veld.network.dnd.model.spell

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseSpellDTO(
    @SerialName("count") val count: Int,
    @SerialName("results") val results: List<SpellDTO>,
)