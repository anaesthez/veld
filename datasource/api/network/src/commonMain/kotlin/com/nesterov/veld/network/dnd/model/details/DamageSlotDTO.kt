package com.nesterov.veld.network.dnd.model.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DamageSlotDTO(
    @SerialName("2") val second: String? = null,
    @SerialName("3") val third: String? = null,
    @SerialName("4") val fourth: String? = null,
    @SerialName("5") val fifth: String? = null,
    @SerialName("6") val sixth: String? = null,
    @SerialName("7") val seventh: String? = null,
    @SerialName("8") val eighth: String? = null,
    @SerialName("9") val ninth: String? = null,
)