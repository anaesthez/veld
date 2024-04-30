package com.nesterov.veld.network.dnd.model.classes.details

import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class StartEquipmentDTO(
    @SerialName("equipment") val startEquipment: ReferenceOptionDTO? = null,
    @SerialName("quantity") val quantity: Int? = null,
)
