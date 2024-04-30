package com.nesterov.veld.network.dnd.model.classes.details

import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class StartingEquipmentOptionsDTO(
    @SerialName("desc") val desc: String? = null,
    @SerialName("choose") val choose: Int? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("from") val from: FromStartingEquipmentDTO? = null,
)

@Serializable
class FromStartingEquipmentDTO(
    @SerialName("option_set_type") val optionSetType: String? = null,
    @SerialName("options") val options: List<FromStartingEquipmentOptionDTO>? = null,
)

@Serializable
class FromStartingEquipmentOptionDTO(
    @SerialName("option_type") val optionType: String? = null,
    @SerialName("items") val items: ChoiceItemDTO? = null,
)

@Serializable
class ChoiceItemDTO(
    @SerialName("option_type") val optionType: String? = null,
    @SerialName("choice") val item: ChoiceDTO? = null,
    @SerialName("of") val ofChoice: ReferenceOptionDTO? = null,
)

@Serializable
class ChoiceDTO(
    @SerialName("desc") val desc: String? = null,
    @SerialName("choose") val choose: Int? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("from") val fromChoice: FromChoiceDTO? = null,
)

@Serializable
class FromChoiceDTO(
    @SerialName("option_set_type") val optionSetType: String? = null,
    @SerialName("equipment_category") val equipmentCategory: ReferenceOptionDTO? = null,
)