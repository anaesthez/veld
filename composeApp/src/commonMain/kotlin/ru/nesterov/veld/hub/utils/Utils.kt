package ru.nesterov.veld.hub.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.nesterov.veld.сore.design_system.Design
import io.github.skeptick.libres.compose.painterResource
import ru.nesterov.veld.hub.model.Page
import ru.nesterov.veld.hub.model.Page.BACKSTORY
import ru.nesterov.veld.hub.model.Page.BESTIARY
import ru.nesterov.veld.hub.model.Page.ClASSES
import ru.nesterov.veld.hub.model.Page.ITEM
import ru.nesterov.veld.hub.model.Page.RACE
import ru.nesterov.veld.hub.model.Page.SPELL
import ru.nesterov.veld.hub.model.Page.TRAIT

inline fun Page.resByPage(): String =
    when (this) {
        ClASSES -> Design.string.hub_page_classes_text
        SPELL -> Design.string.hub_page_spells_text
        ITEM -> Design.string.hub_page_items_text
        BACKSTORY -> Design.string.hub_page_backstory_text
        TRAIT -> Design.string.hub_page_traits_text
        BESTIARY -> Design.string.hub_page_bestiary_text
        RACE -> Design.string.hub_page_races_text
    }

@Composable
inline fun Page.imgByPage(): Painter =
    when (this) {
        ClASSES -> painterResource(Design.image.sword_icon)
        SPELL -> painterResource(Design.image.sword_icon)
        ITEM -> painterResource(Design.image.sword_icon)
        BACKSTORY -> painterResource(Design.image.sword_icon)
        TRAIT -> painterResource(Design.image.sword_icon)
        BESTIARY -> painterResource(Design.image.sword_icon)
        RACE -> painterResource(Design.image.sword_icon)
    }