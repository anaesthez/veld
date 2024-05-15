package com.nesterov.veld.design_system.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val veldColors = staticCompositionLocalOf { veldLight() }

@Stable
class VeldColors(
    materialColors: ColorScheme,
    necromancySpell: Color,
    conjurationSpell: Color,
    evocationSpell: Color,
    illusionSpell: Color,
    abjurationSpell: Color,
    enchantmentSpell: Color,
    divinationSpell: Color,
    transmutationSpell: Color,
    textColorPrimary: Color,
    textColorSecondary: Color,
    textColorTertiary: Color,
    constitution: Color,
    wisdom: Color,
    intelligence: Color,
    dexterity: Color,
    strength: Color,
    charisma: Color,
    isLight: Boolean,
) {
    var materialColors by mutableStateOf(materialColors)
        private set
    private var isLight by mutableStateOf(isLight)
        private set
    var textColorPrimary by mutableStateOf(textColorPrimary)
        private set
    var textColorSecondary by mutableStateOf(textColorSecondary)
        private set
    var textColorTertiary by mutableStateOf(textColorTertiary)
        private set
    var necromancySpell by mutableStateOf(necromancySpell)
        private set
    var conjurationSpell by mutableStateOf(conjurationSpell)
        private set
    var evocationSpell by mutableStateOf(evocationSpell)
        private set
    var illusionSpell by mutableStateOf(illusionSpell)
        private set
    var abjurationSpell by mutableStateOf(abjurationSpell)
        private set
    var divinationSpell by mutableStateOf(divinationSpell)
        private set
    var transmutationSpell by mutableStateOf(transmutationSpell)
        private set
    var enchantmentSpell by mutableStateOf(enchantmentSpell)
        private set
    var constitution by mutableStateOf(constitution)
        private set
    var dexterity by mutableStateOf(dexterity)
        private set
    var charisma by mutableStateOf(charisma)
        private set
    var strength by mutableStateOf(strength)
        private set
    var intelligence by mutableStateOf(intelligence)
        private set
    var wisdom by mutableStateOf(wisdom)
        private set

    override fun toString() = "VeldColors(" +
            "materialColors=$materialColors" +
    ")"

    fun update(colors: VeldColors) {
        materialColors = colors.materialColors
        intelligence = colors.intelligence
        constitution = colors.constitution
        dexterity = colors.dexterity
        charisma = colors.charisma
        strength = colors.strength
        wisdom = colors.wisdom
        textColorPrimary = colors.textColorPrimary
        textColorSecondary = colors.textColorSecondary
        textColorTertiary = colors.textColorTertiary
        necromancySpell = colors.necromancySpell
        conjurationSpell = colors.conjurationSpell
        evocationSpell = colors.evocationSpell
        illusionSpell = colors.illusionSpell
        abjurationSpell = colors.abjurationSpell
        divinationSpell = colors.divinationSpell
        transmutationSpell = colors.transmutationSpell
        enchantmentSpell = colors.enchantmentSpell
    }

    fun copy() = VeldColors(
        materialColors = materialColors,
        textColorPrimary = textColorPrimary,
        textColorSecondary = textColorSecondary,
        textColorTertiary = textColorTertiary,
        necromancySpell = necromancySpell,
        conjurationSpell = conjurationSpell,
        evocationSpell = evocationSpell,
        illusionSpell = illusionSpell,
        abjurationSpell = abjurationSpell,
        divinationSpell = divinationSpell,
        transmutationSpell = transmutationSpell,
        enchantmentSpell = enchantmentSpell,
        constitution = constitution,
        wisdom = wisdom,
        intelligence = intelligence,
        dexterity = dexterity,
        strength = strength,
        charisma = charisma,
        isLight = isLight
    )
}
