package com.nesterov.veld.data

import com.nesterov.veld.domain.model.SpellDomainModel
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO

fun List<ReferenceOptionDTO>.toSpellDomainModel(): List<SpellDomainModel> = map {
    SpellDomainModel(
        name = it.name,
        level = it.level,
        index = it.index,
        url = it.url,
    )
}