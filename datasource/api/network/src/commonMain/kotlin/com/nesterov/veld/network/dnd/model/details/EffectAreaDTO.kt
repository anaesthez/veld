package com.nesterov.veld.network.dnd.model.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EffectAreaDTO(
    @SerialName("size") val size: Int,
    @SerialName("type") val type: String,
)