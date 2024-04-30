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
    @SerialName("option_type") val optionType: String,
    @SerialName("item") val item: ReferenceOptionDTO,
)
