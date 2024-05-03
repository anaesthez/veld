package com.nesterov.veld.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.presentation.model.utils.AreaType
import com.nesterov.veld.presentation.model.utils.MagicSchoolType
import com.nesterov.veld.presentation.model.utils.StatType
import com.nesterov.veld.сore.design_system.images.DesignImages
import com.nesterov.veld.сore.design_system.strings.DesignStrings
import io.github.skeptick.libres.images.Image

const val TRANSPARENCY_ALPHA = 0.2f

internal inline fun MagicSchoolType.getSchoolImageRes(): Image =
    when(this) {
        MagicSchoolType.ABJURATION -> DesignImages.abjuration_school_image
        MagicSchoolType.CONJURATION -> DesignImages.abjuration_school_image
        MagicSchoolType.DIVINATION -> DesignImages.abjuration_school_image
        MagicSchoolType.ENCHANTMENT -> DesignImages.abjuration_school_image
        MagicSchoolType.EVOCATION -> DesignImages.abjuration_school_image
        MagicSchoolType.ILLUSION -> DesignImages.abjuration_school_image
        MagicSchoolType.NECROMANCY -> DesignImages.abjuration_school_image
        MagicSchoolType.TRANSMUTATION -> DesignImages.abjuration_school_image
    }

internal inline fun MagicSchoolType.getSchoolTextRes(): String =
    when(this) {
        MagicSchoolType.ABJURATION -> DesignStrings.spell_details_abjuration_text
        MagicSchoolType.CONJURATION -> DesignStrings.spell_details_conjuration_text
        MagicSchoolType.DIVINATION -> DesignStrings.spell_details_divination_text
        MagicSchoolType.ENCHANTMENT -> DesignStrings.spell_details_enchantment_text
        MagicSchoolType.EVOCATION -> DesignStrings.spell_details_evocation_text
        MagicSchoolType.ILLUSION -> DesignStrings.spell_details_illusion_text
        MagicSchoolType.NECROMANCY -> DesignStrings.spell_details_necromancy_text
        MagicSchoolType.TRANSMUTATION -> DesignStrings.spell_details_transmutation_text
    }

@Composable
internal inline fun MagicSchoolType.getColorBySchool(): Color =
    when(this) {
        MagicSchoolType.ABJURATION -> colors.abjurationSpell
        MagicSchoolType.CONJURATION -> colors.conjurationSpell
        MagicSchoolType.DIVINATION -> colors.divinationSpell
        MagicSchoolType.ENCHANTMENT -> colors.enchantmentSpell
        MagicSchoolType.EVOCATION -> colors.evocationSpell
        MagicSchoolType.ILLUSION -> colors.illusionSpell
        MagicSchoolType.NECROMANCY -> colors.necromancySpell
        MagicSchoolType.TRANSMUTATION -> colors.transmutationSpell
    }

internal inline fun AreaType.getAreaImageRes(): Image =
    when(this) {
        AreaType.SPHERE -> DesignImages.sphere_icon
        AreaType.CONE -> DesignImages.cone_icon
        AreaType.CYLINDER -> DesignImages.cylinder_icon
        AreaType.LINE -> DesignImages.line_icon
        AreaType.CUBE -> DesignImages.cube_icon
    }

internal inline fun StatType.getByStatType(): String =
    when(this) {
        StatType.CASTING -> DesignStrings.spell_details_casting_text
        StatType.DURATION -> DesignStrings.spell_details_duration_text
        StatType.RANGE -> DesignStrings.spell_details_range_text
        StatType.ATTACK_TYPE -> DesignStrings.spell_details_attack_text
        StatType.REQUIRES -> DesignStrings.spell_details_requires_text
    }