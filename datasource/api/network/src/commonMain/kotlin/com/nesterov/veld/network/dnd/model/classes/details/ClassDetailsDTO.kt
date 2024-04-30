package com.nesterov.veld.network.dnd.model.classes.details

import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ClassDetailsDTO(
    @SerialName("index") val index: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("hit_die") val hitDie: Int? = null,
    @SerialName("proficiency_choices") val proficiencyChoices: ProficiencyChoicesDTO? = null,
    @SerialName("proficiencies") val proficiencies: List<ReferenceOptionDTO>? = null,
    @SerialName("starting_equipment") val startEquipment: List<StartEquipmentDTO>? = null,
    @SerialName("starting_equipment_options") val startEquipmentOptions: List<StartingEquipmentOptionsDTO>? = null,
    @SerialName("class_levels") val classLevelsUrl: String? = null,
    @SerialName("multi_classing") val multiClassing: MulticlassDTO? = null,
    @SerialName("spellcasting") val spellCasting: SpellCastDTO? = null,
)