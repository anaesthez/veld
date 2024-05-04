package com.nesterov.veld.presentation.mapper

import com.nesterov.veld.domain.CreatureDomainModel
import com.nesterov.veld.presentation.model.CreaturePresentationModel

fun CreatureDomainModel.toCreaturePresentationModel(): CreaturePresentationModel =
    CreaturePresentationModel(
        index = index,
        name = name,
        url = url,
    )