package com.nesterov.veld.presentation.model

import com.nesterov.veld.presentation.model.utils.AreaType
import com.nesterov.veld.presentation.model.utils.MagicSchool
import com.nesterov.veld.presentation.model.utils.StatType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap

data class SpellDetailsPresentationModel(
    val level: Int,
    val areaSize: Int,
    val url: String,
    val name: String,
    val index: String,
    val range: String,
    val material: String,
    val duration: String,
    val schoolUrl: String,
    val classIndex: String,
    val attackType: String,
    val castingTime: String,
    val schoolIndex: String,
    val areaTypeTitle: String,
    val isRitual: Boolean,
    val isConcentration: Boolean,
    val areaType: AreaType,
    val schoolType: MagicSchool,
    val description: List<String>,
    val higherLevel: List<String>,
    val components: List<String>,
    val statTypes: ImmutableMap<StatType, String>,
    val charClasses: ImmutableList<CharacterClassPresentationModel>,
    val subClasses: ImmutableList<CharacterSubclassPresentationModel>,
    val damage: DamagePresentationModel,
)
