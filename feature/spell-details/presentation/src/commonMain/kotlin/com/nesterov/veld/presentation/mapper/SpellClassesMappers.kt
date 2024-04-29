package com.nesterov.veld.presentation.mapper

import com.nesterov.veld.domain.model.CharacterClassDomainModel
import com.nesterov.veld.domain.model.CharacterSubclassDomainModel
import com.nesterov.veld.presentation.model.CharacterClassPresentationModel
import com.nesterov.veld.presentation.model.CharacterSubclassPresentationModel

fun CharacterClassDomainModel.toCharacterClassPresentationModel(): CharacterClassPresentationModel =
    CharacterClassPresentationModel(
        index = index,
        url = url,
        name = name,
    )

fun CharacterSubclassDomainModel.toCharacterClassPresentationModel(): CharacterSubclassPresentationModel =
    CharacterSubclassPresentationModel(
        index = index,
        url = url,
        name = name,
    )