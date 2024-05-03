package com.nesterov.veld.presentation.model.utils

import com.nesterov.veld.common.AreaTypeContract
import com.nesterov.veld.common.MagicSchoolContract

fun String.getMagicSchoolByContract(): MagicSchoolType =
    when(this) {
        MagicSchoolContract.ABJURATION -> MagicSchoolType.ABJURATION
        MagicSchoolContract.CONJURATION -> MagicSchoolType.CONJURATION
        MagicSchoolContract.EVOCATION -> MagicSchoolType.EVOCATION
        MagicSchoolContract.ILLUSION -> MagicSchoolType.ILLUSION
        MagicSchoolContract.NECROMANCY -> MagicSchoolType.NECROMANCY
        MagicSchoolContract.DIVINATION -> MagicSchoolType.DIVINATION
        MagicSchoolContract.ENCHANTMENT -> MagicSchoolType.ENCHANTMENT
        MagicSchoolContract.TRANSMUTATION -> MagicSchoolType.TRANSMUTATION
        else -> MagicSchoolType.ABJURATION
    }

fun String.getAreaByContract(): AreaType =
    when(this) {
        AreaTypeContract.CONE -> AreaType.CONE
        AreaTypeContract.CUBE -> AreaType.CUBE
        AreaTypeContract.LINE -> AreaType.LINE
        AreaTypeContract.CYLINDER -> AreaType.CYLINDER
        AreaTypeContract.SPHERE -> AreaType.SPHERE
        else -> AreaType.CONE
    }