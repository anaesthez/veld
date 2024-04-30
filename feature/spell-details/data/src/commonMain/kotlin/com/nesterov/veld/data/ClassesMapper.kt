package com.nesterov.veld.data

import com.nesterov.veld.domain.model.CharacterClassDomainModel
import com.nesterov.veld.domain.model.CharacterSubclassDomainModel
import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO

fun ReferenceOptionDTO.toCharacterClassDomainModel(): CharacterClassDomainModel {
    return CharacterClassDomainModel(
        index = index.orEmpty(),
        url = url.orEmpty(),
        name = name.orEmpty(),
    )
}

fun ReferenceOptionDTO.toCharacterSubclassDomainModel(): CharacterSubclassDomainModel {
    return CharacterSubclassDomainModel(
        index = index.orEmpty(),
        url = url.orEmpty(),
        name = name.orEmpty(),
    )
}