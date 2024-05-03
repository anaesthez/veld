package com.nesterov.veld.ui

import com.nesterov.veld.presentation.model.HitDiceType
import com.nesterov.veld.сore.design_system.images.DesignImages
import io.github.skeptick.libres.images.Image

inline fun HitDiceType.getDiceImageByType(): Image =
    when (this) {
        HitDiceType.EMPTY -> DesignImages.dice_6
        HitDiceType.DICE_6 -> DesignImages.dice_6
        HitDiceType.DICE_8 -> DesignImages.dice_8
        HitDiceType.DICE_10 -> DesignImages.dice_10
        HitDiceType.DICE_12 -> DesignImages.dice_12
    }
