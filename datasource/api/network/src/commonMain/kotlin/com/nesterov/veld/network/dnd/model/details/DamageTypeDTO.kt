package com.nesterov.veld.network.dnd.model.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DamageTypeDTO(
    @SerialName("name") val name: String? = null,
    @SerialName("url") val url: String? = null,
)