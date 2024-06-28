package com.nesterov.veld.ui.info

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nesterov.veld.design_system.ui.HeadedBlock
import com.nesterov.veld.сore.design_system.strings.DesignStrings

@Composable
fun InfoScreen(
    passivePerception: Int,
    tremorSense: String,
    blindSight: String,
    darkVision: String,
    trueSight: String,
    size: String,
    type: String,
    subtype: String,
    languages: String,
    alignments: String,
    description: String,
) {
    val sensesList = listOf(
        if (tremorSense.isNotBlank()) DesignStrings.creature_info_tremor_text.format(tremor = tremorSense) else "",
        if (blindSight.isNotBlank()) DesignStrings.creature_info_blind_sight_text.format(blindSight = blindSight) else "",
        if (darkVision.isNotBlank()) DesignStrings.creature_info_dark_vision_text.format(darkVision = darkVision) else "",
        if (trueSight.isNotBlank()) DesignStrings.creature_info_true_sight_text.format(trueSight = trueSight) else ""
    )

    val commonList = listOf(
        if (size.isNotBlank()) DesignStrings.creature_info_size_text.format(size = size) else "",
        if (type.isNotBlank()) DesignStrings.creature_info_type_text.format(type = type) else "",
        if (subtype.isNotBlank()) DesignStrings.creature_info_subtype_text.format(subtype = subtype) else "",
        if (languages.isNotBlank()) DesignStrings.creature_info_languages_text.format(languages = languages) else "",
        if (alignments.isNotBlank()) DesignStrings.creature_info_alignments_text.format(alignments = alignments) else ""
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            if (description.isNotBlank()) {
                DescriptionBlock(
                    description = description,
                )
            }
        }
        item {
            CommonInfo(
                headerText = DesignStrings.creature_info_perception_text.format(
                    passivePerception = passivePerception.toString()
                ),
                infoList = sensesList,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            CommonInfo(
                headerText = DesignStrings.creature_info_common_text,
                infoList = commonList,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun CommonInfo(
    headerText: String,
    infoList: List<String>,
) {
    HeadedBlock(
        modifier = Modifier.padding(horizontal = 16.dp),
        headerText = headerText,
    ) {
        infoList.forEach { infoText ->
            if (infoText.isNotBlank()) {
                InfoText(info = infoText)
            }
        }
    }
}

@Composable
private fun DescriptionBlock(description: String) {
    HeadedBlock(
        modifier = Modifier.fillMaxSize(),
        headerText = DesignStrings.creature_info_description_text,
        fontSize = 22.sp,
        textAlign = TextAlign.Center,
    ) {
        InfoText(
            info = description,
        )
    }
}

// TODO("Redesign planning")
@Composable
private fun InfoText(
    info: String,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp
            ),
        text = info,
    )
}


