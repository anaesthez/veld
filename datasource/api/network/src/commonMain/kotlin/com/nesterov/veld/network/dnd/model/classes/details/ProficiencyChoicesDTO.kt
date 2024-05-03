package com.nesterov.veld.network.dnd.model.classes.details

import com.nesterov.veld.network.dnd.model.spell.ReferenceOptionDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProficiencyChoicesDTO(
    @SerialName("desc") val desc: String? = null,
    @SerialName("choose") val choose: Int? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("from") val from: FromOptionDTO? = null,
)

@Serializable
class FromOptionDTO(
    @SerialName("option_set_type") val optionSetType: String? = null,
    @SerialName("options") val options: List<OptionDTO>? = null,
)

@Serializable
class OptionDTO(
    @SerialName("option_type") val optionType: String? = null,
    @SerialName("item") val item: ReferenceOptionDTO? = null,
    @SerialName("choice") val choice: ChoiceOptionDTO? = null,
)

@Serializable
class ChoiceOptionDTO(
    @SerialName("desc") val description: String? = null,
    @SerialName("type") val type: String? = null,
)
