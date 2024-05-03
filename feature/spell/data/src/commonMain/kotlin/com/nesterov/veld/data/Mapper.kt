package com.nesterov.veld.data

import com.nesterov.veld.domain.model.SpellDomainModel
import com.nesterov.veld.helpers.orZero
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO

fun List<ReferenceOptionDTO>.toSpellDomainModel(): List<SpellDomainModel> = map {
    SpellDomainModel(
        name = it.name.orEmpty(),
        level = it.level.orZero(),
        index = it.index.orEmpty(),
        url = it.url.orEmpty(),
    )
}