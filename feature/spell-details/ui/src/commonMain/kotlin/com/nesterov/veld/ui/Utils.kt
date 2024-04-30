package com.nesterov.veld.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.presentation.model.utils.AreaType
import com.nesterov.veld.presentation.model.utils.MagicSchool
import com.nesterov.veld.presentation.model.utils.StatType
import com.nesterov.veld.сore.design_system.images.DesignImages
import com.nesterov.veld.сore.design_system.strings.DesignStrings
import io.github.skeptick.libres.images.Image

const val TRANSPARENCY_ALPHA = 0.2f

internal fun MagicSchool.getSchoolImageRes(): Image =
    when(this) {
        MagicSchool.ABJURATION -> DesignImages.abjuration_school_image
        MagicSchool.CONJURATION -> DesignImages.abjuration_school_image
        MagicSchool.DIVINATION -> DesignImages.abjuration_school_image
        MagicSchool.ENCHANTMENT -> DesignImages.abjuration_school_image
        MagicSchool.EVOCATION -> DesignImages.abjuration_school_image
        MagicSchool.ILLUSION -> DesignImages.abjuration_school_image
        MagicSchool.NECROMANCY -> DesignImages.abjuration_school_image
        MagicSchool.TRANSMUTATION -> DesignImages.abjuration_school_image
    }

internal fun MagicSchool.getSchoolTextRes(): String =
    when(this) {
        MagicSchool.ABJURATION -> DesignStrings.spell_details_abjuration_text
        MagicSchool.CONJURATION -> DesignStrings.spell_details_conjuration_text
        MagicSchool.DIVINATION -> DesignStrings.spell_details_divination_text
        MagicSchool.ENCHANTMENT -> DesignStrings.spell_details_enchantment_text
        MagicSchool.EVOCATION -> DesignStrings.spell_details_evocation_text
        MagicSchool.ILLUSION -> DesignStrings.spell_details_illusion_text
        MagicSchool.NECROMANCY -> DesignStrings.spell_details_necromancy_text
        MagicSchool.TRANSMUTATION -> DesignStrings.spell_details_transmutation_text
    }

@Composable
internal fun MagicSchool.getColorBySchool(): Color =
    when(this) {
        MagicSchool.ABJURATION -> colors.abjurationSpell
        MagicSchool.CONJURATION -> colors.conjurationSpell
        MagicSchool.DIVINATION -> colors.divinationSpell
        MagicSchool.ENCHANTMENT -> colors.enchantmentSpell
        MagicSchool.EVOCATION -> colors.evocationSpell
        MagicSchool.ILLUSION -> colors.illusionSpell
        MagicSchool.NECROMANCY -> colors.necromancySpell
        MagicSchool.TRANSMUTATION -> colors.transmutationSpell
    }

internal fun AreaType.getAreaImageRes(): Image =
    when(this) {
        AreaType.SPHERE -> DesignImages.sphere_icon
        AreaType.CONE -> DesignImages.cone_icon
        AreaType.CYLINDER -> DesignImages.cylinder_icon
        AreaType.LINE -> DesignImages.line_icon
        AreaType.CUBE -> DesignImages.cube_icon
    }

internal fun StatType.getByStatType(): String =
    when(this) {
        StatType.CASTING -> DesignStrings.spell_details_casting_text
        StatType.DURATION -> DesignStrings.spell_details_duration_text
        StatType.RANGE -> DesignStrings.spell_details_range_text
        StatType.ATTACK_TYPE -> DesignStrings.spell_details_attack_text
        StatType.REQUIRES -> DesignStrings.spell_details_requires_text
    }

@Composable
internal fun HeadedBlock(
    modifier: Modifier = Modifier,
    headerText: String,
    headedContent: @Composable ColumnScope.() -> Unit,
) {
    Column {
        Text(
            modifier = modifier,
            text = headerText,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(Modifier.size(8.dp))
        headedContent()
    }
}