package com.nesterov.veld.presentation.model.utils

import com.nesterov.veld.common.AreaTypeContract
import com.nesterov.veld.common.MagicSchoolContract

fun String.getMagicSchoolByContract(): MagicSchool =
    when(this) {
        MagicSchoolContract.ABJURATION -> MagicSchool.ABJURATION
        MagicSchoolContract.CONJURATION -> MagicSchool.CONJURATION
        MagicSchoolContract.EVOCATION -> MagicSchool.EVOCATION
        MagicSchoolContract.ILLUSION -> MagicSchool.ILLUSION
        MagicSchoolContract.NECROMANCY -> MagicSchool.NECROMANCY
        MagicSchoolContract.DIVINATION -> MagicSchool.DIVINATION
        MagicSchoolContract.ENCHANTMENT -> MagicSchool.ENCHANTMENT
        MagicSchoolContract.TRANSMUTATION -> MagicSchool.TRANSMUTATION
        else -> MagicSchool.ABJURATION
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