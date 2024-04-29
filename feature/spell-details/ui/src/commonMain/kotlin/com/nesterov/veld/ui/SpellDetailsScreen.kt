package com.nesterov.veld.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.nesterov.veld.design_system.theme.VeldIcons
import com.nesterov.veld.design_system.theme.VeldTheme.colors
import com.nesterov.veld.design_system.ui.VeldClassesLazyRow
import com.nesterov.veld.design_system.ui.VeldErrorScreen
import com.nesterov.veld.design_system.ui.VeldProgressBar
import com.nesterov.veld.presentation.SpellDetailsComponent
import com.nesterov.veld.presentation.SpellDetailsStore
import com.nesterov.veld.presentation.model.CharacterClassPresentationModel
import com.nesterov.veld.presentation.model.CharacterSubclassPresentationModel
import com.nesterov.veld.presentation.model.utils.AreaType
import com.nesterov.veld.presentation.model.utils.MagicSchool
import com.nesterov.veld.presentation.model.utils.SlotType
import com.nesterov.veld.presentation.model.utils.StatType
import com.nesterov.veld.ui.blocks.DamageStatBlock
import com.nesterov.veld.ui.blocks.SpellStatBlock
import io.github.skeptick.libres.compose.painterResource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap

const val MAX_POSSIBLE_SPELL_LEVEL = 9
const val ROTATION_SPELL_TYPE = 45f

@Composable
fun SpellDetailsScreen(component: SpellDetailsComponent) {
    val state by component.state.subscribeAsState()
    val lazyClassesListState = rememberLazyListState()
    val lazySubclassesListState = rememberLazyListState()

    val onObtainEvent: (SpellDetailsComponent.Event) -> Unit = remember {
        { event ->
            component.onObtainEvent(event)
        }
    }

    when(state.screenState) {
        is SpellDetailsStore.ScreenState.Failure -> VeldErrorScreen()
        is SpellDetailsStore.ScreenState.Loading -> VeldProgressBar()
        is SpellDetailsStore.ScreenState.Success -> {
            val details = (state.screenState as SpellDetailsStore.ScreenState.Success)
            SpellDetailsScreenStateful(
                lazyClassesListState = lazyClassesListState,
                lazySubclassesListState = lazySubclassesListState,
                charClasses = details.spell.charClasses,
                subclasses = details.spell.subClasses,
                statTypes = details.spell.statTypes,
                damageSlots = details.spell.damage.damageSlots,
                damageType = details.spell.damage.damageType.name,
                components = details.spell.components,
                description = details.spell.description.joinToString(),
                level = details.spell.level,
                materials = details.spell.material,
                school = details.spell.schoolType,
                spellName = details.spell.name,
                areaTypeTitle = details.spell.areaTypeTitle,
                rangeDistance = details.spell.areaSize.toString(),
                areaType = details.spell.areaType,
                schoolColor = details.spell.schoolType.getColorBySchool(),
                onObtainEvent = onObtainEvent,
            )
        }
    }
}

@Composable
private fun SpellDetailsScreenStateful(
    lazyClassesListState: LazyListState,
    lazySubclassesListState: LazyListState,
    charClasses: ImmutableList<CharacterClassPresentationModel>,
    subclasses: ImmutableList<CharacterSubclassPresentationModel>,
    statTypes: ImmutableMap<StatType, String>,
    damageSlots: ImmutableMap<SlotType, String>,
    components: List<String>,
    schoolColor: Color,
    school: MagicSchool,
    areaType: AreaType,
    areaTypeTitle: String,
    rangeDistance: String,
    description: String,
    damageType: String,
    spellName: String,
    materials: String,
    level: Int,
    onObtainEvent: (SpellDetailsComponent.Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SpellToolBar(
            onBackClick = { onObtainEvent(SpellDetailsComponent.Event.OnBackClick) }
        )
        SpellDetailsHeader(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            spellName = spellName,
            schoolColor = schoolColor,
            school = school,
            level = level,
        )
        MagicSchoolHeader(
            magicSchool = school,
        )
        if (statTypes.isNotEmpty()) {
            SpellStatBlock(
                statTypes = statTypes,
                schoolColor = schoolColor,
            )
        }
        if (damageSlots.isNotEmpty()) {
            DamageStatBlock(
                areaTypeTitle = areaTypeTitle,
                rangeDistance = rangeDistance,
                damageSlots = damageSlots,
                schoolColor = schoolColor,
                damageType = damageType,
                areaType = areaType,
            )
        }
        if (charClasses.isNotEmpty()) {
            ClassesList(
                lazyListState = lazyClassesListState,
                charClasses = charClasses,
                onClassClick = { onObtainEvent(SpellDetailsComponent.Event.OnClassClick) }
            )
        }
        if (subclasses.isNotEmpty()) {
            SubclassesList(
                lazyListState = lazySubclassesListState,
                subclasses = subclasses,
                onSubclassClick = { onObtainEvent(SpellDetailsComponent.Event.OnSubclassClick) }
            )
        }
        SpellComponentsBlock(
            components = components,
            materials = materials,
            schoolColor = schoolColor,
        )
        SpellDescription(
            description = description,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpellToolBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    imageVector = VeldIcons.backArrow,
                    tint = colors.necromancySpell,
                    contentDescription = null,
                )
            }
        },
    )
}

@Composable
private fun SpellDetailsHeader(
    modifier: Modifier = Modifier,
    schoolColor: Color,
    spellName: String,
    level: Int,
    school: MagicSchool,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val headerModifier = Modifier.fillMaxWidth(0.6f)
        SpellHeaderImage(
            modifier = headerModifier,
            school = school,
            schoolColor = schoolColor,
            spellName = spellName,
        )
        Spacer(Modifier.height(16.dp))
        SpellLevelCircle(
            modifier = headerModifier,
            level = level,
        )
    }
}

@Composable
private fun SpellHeaderImage(
    modifier: Modifier = Modifier,
    school: MagicSchool,
    schoolColor: Color,
    spellName: String,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = schoolColor.copy(alpha = 0.2f)
        )
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(216.dp)
                .padding(horizontal = 24.dp),
            painter = painterResource(school.getSchoolImageRes()),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            text = spellName,
        )
    }
}

@Composable
private fun MagicSchoolHeader(
    magicSchool: MagicSchool,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = magicSchool.getSchoolTextRes(),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        textDecoration = TextDecoration.Underline,
    )
}

@Composable
private fun SpellLevelCircle(
    modifier: Modifier = Modifier,
    level: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(MAX_POSSIBLE_SPELL_LEVEL) { currentLevelIndex ->
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(
                        if (currentLevelIndex < level) {
                            colors.necromancySpell
                        } else {
                            colors.necromancySpell.copy(alpha = 0.2f)
                        },
                        CircleShape
                    )
            )
        }
    }
}

@Composable
private fun ClassesList(
    lazyListState: LazyListState,
    charClasses: ImmutableList<CharacterClassPresentationModel>,
    onClassClick: () -> Unit,
) {
    HeadedBlock(headerText = "Classes") {
        VeldClassesLazyRow(
            lazyRowListState = lazyListState,
            charClasses = charClasses,
        ) { classItem ->
            ClassItem(
                className = classItem.name,
                onClassClick = onClassClick,
            )
        }
    }
}

@Composable
private fun SubclassesList(
    lazyListState: LazyListState,
    subclasses: ImmutableList<CharacterSubclassPresentationModel>,
    onSubclassClick: () -> Unit,
) {
    HeadedBlock(headerText = "Subclasses") {
        VeldClassesLazyRow(
            lazyRowListState = lazyListState,
            charClasses = subclasses,
        ) { subclassItem ->
            ClassItem(
                className = subclassItem.name,
                onClassClick = onSubclassClick,
            )
        }
    }
}

@Composable
private fun ClassItem(
    modifier: Modifier = Modifier,
    className: String,
    onClassClick: () -> Unit,
) {
    ListItem(
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .semantics(mergeDescendants = true) { }
            .clickable(enabled = true, onClick = onClassClick),
        leadingContent = {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = null,
            )
        },
        headlineContent = {
            Text(
                text = className,
                fontWeight = FontWeight.SemiBold,
                color = colors.textColorPrimary,
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = Color(0xFF717ED4)
        )
    )
}

@Composable
private fun SpellComponentsBlock(
    components: List<String>,
    schoolColor: Color,
    materials: String,
) {
    HeadedBlock(headerText = "Сomponents") {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            components.forEach {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                        .background(
                            color = schoolColor.copy(alpha = TRANSPARENCY_ALPHA),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    text = it,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = materials,
        )
    }
}

@Composable
private fun SpellDescription(description: String) {
    HeadedBlock(headerText = "Description") {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
        )
    }
}
