package com.nesterov.veld.network.dnd.model.spell.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EffectAreaDTO(
    @SerialName("size") val size: Int,
    @SerialName("type") val type: String,
)