package com.nesterov.veld.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.design_system.ui.HeadedBlock
import com.nesterov.veld.design_system.ui.VeldAppBar
import com.nesterov.veld.design_system.ui.VeldFailureScreen
import com.nesterov.veld.design_system.ui.VeldItemsLazyRow
import com.nesterov.veld.design_system.ui.VeldListItem
import com.nesterov.veld.design_system.ui.VeldProgressBar
import com.nesterov.veld.presentation.ClassDetailsComponent
import com.nesterov.veld.presentation.ClassDetailsStore
import com.nesterov.veld.presentation.model.ChoiceProficiencyPresentationModel
import com.nesterov.veld.presentation.model.HitDiceType
import com.nesterov.veld.presentation.model.PrerequisitePresentationModel
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

    val onObtainEvent: (ClassDetailsComponent.Event) -> Unit = remember {
        { event ->
            component.obtainEvent(event)
        }
    }

    when (state.screenState) {
        ClassDetailsStore.ScreenState.Loading -> {
            VeldProgressBar()
        }

        ClassDetailsStore.ScreenState.Failure -> {
            VeldFailureScreen(
                errorText = DesignStrings.classes_details_spell_failure,
                onBackClick = { onObtainEvent(ClassDetailsComponent.Event.OnBackClick) },
                onRetryClick = { onObtainEvent(ClassDetailsComponent.Event.OnRetryClick) }
            )
        }

        is ClassDetailsStore.ScreenState.Success -> {
            val details = (state.screenState as ClassDetailsStore.ScreenState.Success).classDetails
            ClassDetailsScreenStateful(
                charClassName = details.charClassName,
                diceType = details.hitDie,
                spellCast = details.spellCast,
                prerequisites = details.multiClass.prerequisites.toImmutableList(),
                commonProficiencies = details.commonProficiencies.toImmutableList(),
                choiceProficiencies = details.choiceProficiencies.toImmutableList(),
                lazyListState = lazyListState,
                onObtainEvent = onObtainEvent,
            )
        }
    }
}

@Composable
private fun ClassDetailsScreenStateful(
    choiceProficiencies: ImmutableList<ChoiceProficiencyPresentationModel>,
    commonProficiencies: ImmutableList<ProficiencyPresentationModel>,
    prerequisites: ImmutableList<PrerequisitePresentationModel>,
    spellCast: SpellCastPresentationModel,
    lazyListState: LazyListState,
    charClassName: String,
    diceType: HitDiceType,
    onObtainEvent: (ClassDetailsComponent.Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        DetailsTopAppBar(
            charClassName = charClassName,
            onBackClick = { onObtainEvent(ClassDetailsComponent.Event.OnBackClick) },
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
            onProficiencyClick = { onObtainEvent(ClassDetailsComponent.Event.OnProficiencyClick) },
            lazyListState = lazyListState,
        )
        DetailsMulticlassBlock(
            prerequisites = prerequisites,
        )
        if (spellCast.spellAbilityTitle.isNotBlank()) {
            DetailsSpellCastBlock(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                spellCast = spellCast,
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
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
        }
    }
}

@Composable
private fun DetailsMulticlassBlock(
    prerequisites: ImmutableList<PrerequisitePresentationModel>,
) {
    val multiclassModifier = Modifier.padding(horizontal = 16.dp)
    HeadedBlock(
        modifier = multiclassModifier,
        headerText = DesignStrings.classes_details_prerequisites_header,
    ) {
        Text(
            modifier = multiclassModifier,
            text = DesignStrings.classes_details_prerequisites_descr,
            fontSize = 16.sp,
        )
        Spacer(Modifier.height(8.dp))
        prerequisites.fastForEach { prerequisite ->
            MulticlassPrerequisite(
                prerequisiteTitle = prerequisite.title,
                minimumScore = prerequisite.minimumScore.toString(),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun MulticlassPrerequisite(
    prerequisiteTitle: String,
    minimumScore: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Blue,
        )
    ) {
        Spacer(Modifier.height(4.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = prerequisiteTitle,
            fontWeight = FontWeight.SemiBold,
            color = colors.textColorPrimary,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = minimumScore,
            fontWeight = FontWeight.SemiBold,
            color = colors.textColorPrimary,
        )
        Spacer(Modifier.height(4.dp))
    }
}