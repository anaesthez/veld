package com.nesterov.veld.data

import com.nesterov.veld.domain.CreatureDomainModel
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO

fun List<ReferenceOptionDTO>.toCreatureDomainModel(): List<CreatureDomainModel> =
    map {
        CreatureDomainModel(
            index = it.index.orEmpty(),
            name = it.name.orEmpty(),
            url = it.url.orEmpty(),
        )
    }
