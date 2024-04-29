package com.nesterov.veld.presentation.model

import com.nesterov.veld.presentation.model.utils.SlotType
import kotlinx.collections.immutable.ImmutableMap

class DamagePresentationModel(
    val damageType: DamageTypePresentationModel,
    val damageSlots: ImmutableMap<SlotType, String>,
)