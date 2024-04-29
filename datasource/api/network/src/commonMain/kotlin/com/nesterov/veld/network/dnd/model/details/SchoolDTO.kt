package com.nesterov.veld.network.dnd.model.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SchoolDTO(
    @SerialName("index") val index: String? = null,
    @SerialName("level") val level: Int? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("name") val name: String? = null,
)