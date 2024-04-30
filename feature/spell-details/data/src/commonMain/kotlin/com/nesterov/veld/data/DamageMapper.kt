package com.nesterov.veld.data

import com.nesterov.veld.domain.model.DamageDomainModel
import com.nesterov.veld.domain.model.DamageSlotDomainModel
import com.nesterov.veld.domain.model.DamageTypeDomainModel
import com.nesterov.veld.network.dnd.model.spell.details.DamageDTO
import com.nesterov.veld.network.dnd.model.spell.details.DamageSlotDTO
import com.nesterov.veld.network.dnd.model.spell.details.DamageTypeDTO

fun DamageDTO.toDamageDomainModel(): DamageDomainModel {
    return DamageDomainModel(
        damageSlot = damageSlots?.toDamageSlotDomainModel() ?: DamageSlotDomainModel("", "", "", "", "", "", "", ""),
        damageType = damageType?.toDamageTypeDomainModel() ?: DamageTypeDomainModel("", "")
    )
}

fun DamageSlotDTO.toDamageSlotDomainModel(): DamageSlotDomainModel {
    return DamageSlotDomainModel(
        second = second.orEmpty(),
        third = third.orEmpty(),
        fourth = fourth.orEmpty(),
        fifth = fifth.orEmpty(),
        sixth = sixth.orEmpty(),
        seventh = seventh.orEmpty(),
        eighth = eighth.orEmpty(),
        ninth = ninth.orEmpty(),
    )
}

fun DamageTypeDTO.toDamageTypeDomainModel(): DamageTypeDomainModel {
    return DamageTypeDomainModel(
        name = name.orEmpty(),
        url = url.orEmpty(),
    )
}