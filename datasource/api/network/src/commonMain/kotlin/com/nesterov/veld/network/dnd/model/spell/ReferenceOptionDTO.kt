package com.nesterov.veld.network.dnd.model.spell

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ReferenceOptionDTO(
    @SerialName("index") val index: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("level") val level: Int? = null,
    @SerialName("url") val url: String? = null,
)