package com.nesterov.veld.data

import com.nesterov.veld.domain.model.CharacterClassDomainModel
import com.nesterov.veld.domain.model.CharacterSubclassDomainModel
import com.nesterov.veld.helpers.orZero
import com.nesterov.veld.network.dnd.model.details.ClassDTO
import com.nesterov.veld.network.dnd.model.details.SubclassDTO

fun ClassDTO.toCharacterClassDomainModel(): CharacterClassDomainModel {
    return CharacterClassDomainModel(
        index = index.orEmpty(),
        url = url.orEmpty(),
        name = name.orEmpty(),
    )
}

fun SubclassDTO.toCharacterSubclassDomainModel(): CharacterSubclassDomainModel {
    return CharacterSubclassDomainModel(
        index = index.orEmpty(),
        url = url.orEmpty(),
        name = name.orEmpty(),
    )
}