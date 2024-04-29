package com.nesterov.veld.presentation.mapper

import com.nesterov.veld.domain.model.DamageDomainModel
import com.nesterov.veld.domain.model.DamageTypeDomainModel
import com.nesterov.veld.presentation.model.DamagePresentationModel
import com.nesterov.veld.presentation.model.DamageTypePresentationModel
import com.nesterov.veld.presentation.model.utils.SlotType
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableMap

fun DamageDomainModel.toDamagePresentationModel(): DamagePresentationModel =
    DamagePresentationModel(
        damageSlots = mapOf(
            SlotType.SECOND to damageSlot.second,
            SlotType.THIRD to damageSlot.third,
            SlotType.FOURTH to damageSlot.fourth,
            SlotType.FIFTH to damageSlot.fifth,
            SlotType.SIXTH to damageSlot.sixth,
            SlotType.SEVENTH to damageSlot.seventh,
            SlotType.EIGHTH to damageSlot.eighth,
            SlotType.NINTH to damageSlot.ninth,
        ).filter { it.value.isNotEmpty() }.toImmutableMap(),
        damageType = damageType.toDamageTypePresentationModel(),
    )


fun DamageTypeDomainModel.toDamageTypePresentationModel(): DamageTypePresentationModel =
    DamageTypePresentationModel(
        name = name,
        url = url,
    )