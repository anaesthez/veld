package com.nesterov.veld.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.design_system.ui.HeadedBlock
import com.nesterov.veld.design_system.ui.VeldAppBar
import com.nesterov.veld.design_system.ui.VeldItemsLazyRow
import com.nesterov.veld.design_system.ui.VeldListItem
import com.nesterov.veld.design_system.ui.VeldProgressBar
import com.nesterov.veld.presentation.ClassDetailsComponent
import com.nesterov.veld.presentation.ClassDetailsStore
import com.nesterov.veld.presentation.model.ChoiceProficiencyPresentationModel
import com.nesterov.veld.presentation.model.HitDiceType
import com.nesterov.veld.presentation.model.ProficiencyPresentationModel
import com.nesterov.veld.presentation.model.SpellCastPresentationModel
import com.nesterov.veld.сore.design_system.strings.DesignStrings
import io.github.skeptick.libres.compose.painterResource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun ClassDetailsScreen(component: ClassDetailsComponent) {
    val state by component.state.subscribeAsState()
    val lazyListState = rememberLazyListState()

    when (state.screenState) {
        ClassDetailsStore.ScreenState.Failure -> {
            println("failed")
        }

        ClassDetailsStore.ScreenState.Loading -> VeldProgressBar()
        is ClassDetailsStore.ScreenState.Success -> {
            val details = (state.screenState as ClassDetailsStore.ScreenState.Success).classDetails
            println(details)
            ClassDetailsScreenStateful(
                charClassName = details.charClassName,
                diceType = details.hitDie,
                spellCast = details.spellCast,
                commonProficiencies = details.commonProficiencies.toImmutableList(),
                choiceProficiencies = details.choiceProficiencies.toImmutableList(),
                lazyListState = lazyListState,
                onProficiencyClick = { },
                onBackClick = { }
            )
        }
    }
}

@Composable
private fun ClassDetailsScreenStateful(
    choiceProficiencies: ImmutableList<ChoiceProficiencyPresentationModel>,
    commonProficiencies: ImmutableList<ProficiencyPresentationModel>,
    spellCast: SpellCastPresentationModel,
    lazyListState: LazyListState,
    charClassName: String,
    diceType: HitDiceType,
    onBackClick: () -> Unit,
    onProficiencyClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DetailsTopAppBar(
            charClassName = charClassName,
            onBackClick = onBackClick,
        )
        DetailsClassDiceImage(
            modifier = Modifier.fillMaxWidth(),
            diceType = diceType,
        )
        DetailsProficiencyOptionsBlock(
            choiceProficiencies = choiceProficiencies,
        )
        DetailsProficiencyBlock(
            commonProficiencies = commonProficiencies,
            onProficiencyClick = onProficiencyClick,
            lazyListState = lazyListState,
        )
        if (spellCast.spellAbilityTitle.isNotBlank()) {
            DetailsSpellCastBlock(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                spellCast = spellCast,
            )
        }
    }
}

@Composable
private fun DetailsTopAppBar(
    charClassName: String,
    onBackClick: () -> Unit,
) {
    VeldAppBar(
        titleText = charClassName,
        onBackClick = onBackClick,
    )
}

@Composable
private fun DetailsClassDiceImage(
    modifier: Modifier = Modifier,
    diceType: HitDiceType,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .padding(12.dp)
                .size(150.dp),
            painter = painterResource(diceType.getDiceImageByType()),
            tint = Color.Blue,
            contentDescription = null,
        )
    }
}

@Composable
private fun DetailsProficiencyBlock(
    lazyListState: LazyListState,
    commonProficiencies: ImmutableList<ProficiencyPresentationModel>,
    onProficiencyClick: () -> Unit,
) {
    HeadedBlock(
        modifier = Modifier.padding(horizontal = 16.dp),
        headerText = DesignStrings.classes_details_proficiency_header
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = DesignStrings.classes_details_proficiency_descr,
            textAlign = TextAlign.Start,
        )
        VeldItemsLazyRow(
            lazyRowListState = lazyListState,
            itemsList = commonProficiencies
        ) { proficiency ->
            VeldListItem(
                title = proficiency.title,
                backgroundColor = Color.Blue,
                onItemClick = onProficiencyClick,
            )
        }
    }
}

@Composable
private fun DetailsProficiencyOptionsBlock(
    choiceProficiencies: ImmutableList<ChoiceProficiencyPresentationModel>,
) {
    HeadedBlock(
        modifier = Modifier.padding(horizontal = 16.dp),
        headerText = DesignStrings.classes_details_options_header,
    ) {
        choiceProficiencies.forEach { option ->
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = option.description
            )
            VeldItemsLazyRow(
                modifier = Modifier.fillMaxWidth(),
                itemsList = option.options.toImmutableList(),
            ) { proficiency ->
                VeldListItem(
                    title = proficiency.title,
                    backgroundColor = Color.Blue,
                    onItemClick = {

                    },
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun DetailsSpellCastBlock(
    modifier: Modifier = Modifier,
    spellCast: SpellCastPresentationModel,
) {
    Column {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = DesignStrings.classes_details_spell_cast_header,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = spellCast.spellAbilityTitle,
                fontSize = 16.sp,
                color = colors.textColorTertiary,
                fontWeight = FontWeight.SemiBold,
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        spellCast.info.forEach { infoBlock ->
            Text(
                modifier = modifier,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                text = infoBlock.title,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = modifier,
                text = infoBlock.description,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}