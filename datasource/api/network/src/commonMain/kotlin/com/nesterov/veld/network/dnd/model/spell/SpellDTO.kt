package com.nesterov.veld.network.dnd.model.spell

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SpellDTO(
    @SerialName("index") val index: String,
    @SerialName("name") val name: String,
    @SerialName("level") val level: Int,
    @SerialName("url") val url: String,
)