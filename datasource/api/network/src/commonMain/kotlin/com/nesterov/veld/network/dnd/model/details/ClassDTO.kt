package com.nesterov.veld.network.dnd.model.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ClassDTO(
    @SerialName("index") val index: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("name") val name: String? = null,
)