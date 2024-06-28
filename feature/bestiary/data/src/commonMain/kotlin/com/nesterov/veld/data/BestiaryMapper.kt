package com.nesterov.veld.data

import com.nesterov.veld.creature.entity.CreatureEntity
import com.nesterov.veld.domain.CreatureDomainModel
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import kotlin.jvm.JvmName

@JvmName("fromReferenceDTOToCreatureDomainModel")
fun List<ReferenceOptionDTO>.toCreatureDomainModel(): List<CreatureDomainModel> =
    map {
        CreatureDomainModel(
            index = it.index.orEmpty(),
            name = it.name.orEmpty(),
            url = it.url.orEmpty(),
        )
    }

@JvmName("fromEntityToCreatureDomainModel")
fun List<CreatureEntity>.toCreatureDomainModel(): List<CreatureDomainModel> =
    map {
        CreatureDomainModel(
            index = it.index,
            name = it.name,
            url = it.url,
        )
    }
