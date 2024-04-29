package com.nesterov.veld.design_system.theme

fun veldLight() = VeldColors(
    materialColors = materialLightColorScheme,
    textColorPrimary = vi_theme_light_textColorPrimary,
    textColorSecondary = vi_theme_light_textColorSecondary,
    textColorTertiary = vi_theme_light_textColorTertiary,
    necromancySpell = vi_theme_light_necromancySpell,
    conjurationSpell = vi_theme_light_conjurationSpell,
    evocationSpell = vi_theme_light_evocationSpell,
    illusionSpell = vi_theme_light_illusionSpell,
    abjurationSpell = vi_theme_light_abjurationSpell,
    divinationSpell = vi_theme_light_divinationSpell,
    transmutationSpell = vi_theme_light_transmutationSpell,
    enchantmentSpell = vi_theme_light_enchantmentSpell,
    isLight = true,
)

fun veldDark() = VeldColors(
    materialColors = materialDarkColorScheme,
    textColorPrimary = vi_theme_dark_textColorPrimary,
    textColorSecondary = vi_theme_dark_textColorSecondary,
    textColorTertiary = vi_theme_dark_textColorTertiary,
    necromancySpell = vi_theme_dark_necromancySpell,
    conjurationSpell = vi_theme_dark_conjurationSpell,
    evocationSpell = vi_theme_dark_evocationSpell,
    illusionSpell = vi_theme_dark_illusionSpell,
    abjurationSpell = vi_theme_dark_abjurationSpell,
    divinationSpell = vi_theme_dark_divinationSpell,
    transmutationSpell = vi_theme_dark_transmutationSpell,
    enchantmentSpell = vi_theme_dark_enchantmentSpell,
    isLight = false,
)