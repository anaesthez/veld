package com.nesterov.veld.network.dnd.model.spell.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DamageDTO(
    @SerialName("damage_type") val damageType: DamageTypeDTO? = null,
    @SerialName("damage_at_slot_level") val damageSlots: DamageSlotDTO? = null,
)