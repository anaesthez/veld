package com.nesterov.veld.presentation.mapper

import com.nesterov.veld.domain.model.SpellDomainModel
import com.nesterov.veld.presentation.model.SpellPresentationModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

fun List<SpellDomainModel>.toSpellPresentationModel(): ImmutableList<SpellPresentationModel> = map {
    SpellPresentationModel(
        name = it.name,
        level = it.level,
        index = it.index,
        url = it.url,
    )
}.toImmutableList()