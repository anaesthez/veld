package com.nesterov.veld.data

import com.nesterov.veld.domain.model.DamageDomainModel
import com.nesterov.veld.domain.model.DamageSlotDomainModel
import com.nesterov.veld.domain.model.DamageTypeDomainModel
import com.nesterov.veld.domain.model.SpellDetailsDomainModel
import com.nesterov.veld.helpers.orFalse
import com.nesterov.veld.helpers.orZero
import com.nesterov.veld.network.dnd.model.spell.details.SpellDetailsDTO

fun SpellDetailsDTO.toSpellDomainModel(): SpellDetailsDomainModel =
    SpellDetailsDomainModel(
        areaSize = effectArea?.size.orZero(),
        level = level.orZero(),
        url = url.orEmpty(),
        name = name.orEmpty(),
        index = index.orEmpty(),
        range = range.orEmpty(),
        material = material.orEmpty(),
        duration = duration.orEmpty(),
        schoolUrl = school?.url.orEmpty(),
        attackType = attackType.orEmpty(),
        higherLevel = higherLevel.orEmpty(),
        description = description.orEmpty(),
        castingTime = castingTime.orEmpty(),
        schoolIndex = school?.index.orEmpty(),
        isRitual = isRitual.orFalse(),
        isConcentration = isConcentration.orFalse(),
        areaType = effectArea?.type.orEmpty(),
        components = components.orEmpty(),
        subClasses = subclasses?.mapNotNull { it?.toCharacterSubclassDomainModel() }.orEmpty(),
        charClasses = charClasses?.mapNotNull { it?.toCharacterClassDomainModel() }.orEmpty(),
        damage = damage?.toDamageDomainModel() ?: DamageDomainModel(
            damageSlot = DamageSlotDomainModel("", "", "", "", "", "", "", ""),
            damageType = DamageTypeDomainModel("", ""),
        ),
)

