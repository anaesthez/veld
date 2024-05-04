package com.nesterov.veld.network.dnd.model.bestiary

import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ResponseBestiaryDTO(
    @SerialName("count") val count: Int,
    @SerialName("results") val results: List<ReferenceOptionDTO>,
)