package com.nesterov.veld.data

import com.nesterov.veld.domain.model.CharacterClassDomainModel
import com.nesterov.veld.domain.model.CharacterSubclassDomainModel

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