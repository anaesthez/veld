package ru.nesterov.veld.hub

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent
import com.nesterov.veld.presentation.BestiaryComponent
import com.nesterov.veld.presentation.BestiaryComponentImpl
import com.nesterov.veld.presentation.ClassesComponent
import com.nesterov.veld.presentation.ClassesComponentImpl
import com.nesterov.veld.presentation.ItemComponent
import com.nesterov.veld.presentation.ItemComponentImpl
import com.nesterov.veld.presentation.RaceComponent
import com.nesterov.veld.presentation.RaceComponentImpl
import com.nesterov.veld.presentation.SpellComponent
import com.nesterov.veld.presentation.SpellComponentImpl
import com.nesterov.veld.presentation.di.BestiaryDependencies
import com.nesterov.veld.presentation.di.SpellDependencies
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import ru.nesterov.veld.hub.model.Page
import ru.nesterov.veld.hub.model.SelectablePageUiModel
import ru.nesterov.veld.hub.store.HubStore
import ru.nesterov.veld.hub.store.HubStoreFactory

@Stable
interface HubRootComponent {
    @OptIn(ExperimentalDecomposeApi::class)
    val pages: Value<ChildPages<*, Pages>>
    val state: Value<HubStore.State>

    fun onObtainEvent(event: Event)

    sealed interface Event {
        data class OnInputQuery(val input: String): Event
        data class OnSelectPage(val index: Int): Event
        data object OnSearchQuery: Event
        data object NavigateFilter: Event
    }

    sealed interface Pages {
        data class Spells(val component: SpellComponent): Pages
        data class Items(val component: ItemComponent): Pages
        data class Classes(val component: ClassesComponent): Pages
        data class Race(val component: RaceComponent): Pages
        data class Bestiary(val component: BestiaryComponent) : Pages
    }

    sealed interface Action {
        data class NavigateSpellDetails(val spellIndex: String): Action
        data class NavigateClassDetails(val classIndex: String) : Action
        data class NavigateCreatureDetails(val creatureIndex: String) : Action
    }
}

@OptIn(ExperimentalDecomposeApi::class)
class HubRootComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val dependencies: SpellDependencies,
    private val bestiaryDependencies: BestiaryDependencies,
    private val onAction: (HubRootComponent.Action) -> Unit,
) : BaseComponent(componentContext), HubRootComponent {
    private val pagesNavigation = PagesNavigation<Configuration>()
    override val pages: Value<ChildPages<Configuration, HubRootComponent.Pages>> =
        childPages(
            source = pagesNavigation,
            serializer = serializer<Configuration>(),
            initialPages = ::initialPages,
            childFactory = ::childFactory
        )

    private fun initialPages() = Pages(
        items = pagesMap.values.toList(),
        selectedIndex = pagesMap.values.indices.first,
    )

    private fun childFactory(page: Configuration, ctx: ComponentContext): HubRootComponent.Pages =
        when (page) {
            Configuration.Classes -> HubRootComponent.Pages.Classes(
                ClassesComponentImpl(
                    componentContext = ctx,
                    storeFactory = storeFactory,
                    action = ::onClassesAction
                )
            )

            Configuration.Spells -> HubRootComponent.Pages.Spells(
                SpellComponentImpl(
                    componentContext = ctx,
                    storeFactory = storeFactory,
                    dependencies = dependencies,
                    action = ::onSpellAction,
                )
            )

            Configuration.Items -> HubRootComponent.Pages.Items(
                ItemComponentImpl(
                    componentContext = ctx,
                    storeFactory = storeFactory,
                )
            )

            Configuration.Race -> HubRootComponent.Pages.Race(
                RaceComponentImpl(
                    componentContext = ctx,
                    storeFactory = storeFactory,
                )
            )

            Configuration.Bestiary -> HubRootComponent.Pages.Bestiary(
                BestiaryComponentImpl(
                    componentContext = ctx,
                    storeFactory = storeFactory,
                    dependencies = bestiaryDependencies,
                    action = ::onBestiaryAction,
                )
            )
        }

    private fun onSpellAction(action: SpellComponent.Action) =
        when (action) {
            is SpellComponent.Action.NavigateSpellDetails -> onAction(
                HubRootComponent.Action.NavigateSpellDetails(action.index)
            )
        }

    private fun onClassesAction(action: ClassesComponent.Action) =
        when (action) {
            is ClassesComponent.Action.NavigateClassDetails -> onAction(
                HubRootComponent.Action.NavigateClassDetails(action.index)
            )
        }

    private fun onBestiaryAction(action: BestiaryComponent.Action) =
        when (action) {
            is BestiaryComponent.Action.OnCreatureClick -> onAction(
                HubRootComponent.Action.NavigateCreatureDetails(action.index)
            )
        }

    private val hubStore = instanceKeeper.getStore {
        HubStoreFactory(
            storeFactory = storeFactory,
            initialPages = pagesMap.keys.toImmutableList()
        ).create()
    }
    override val state: Value<HubStore.State> = hubStore.asValue()

    override fun onObtainEvent(event: HubRootComponent.Event) =
        when (event) {
            is HubRootComponent.Event.OnInputQuery -> {
                hubStore.accept(HubStore.Intent.InputQuery(event.input))
            }

            is HubRootComponent.Event.OnSelectPage -> {
                selectPage(event.index)
            }

            HubRootComponent.Event.OnSearchQuery -> {
                search()
            }

            HubRootComponent.Event.NavigateFilter -> {

            }
        }


    private fun selectPage(index: Int) {
        pagesNavigation.select(index) { _, _ ->
            hubStore.accept(HubStore.Intent.SelectPage(index))
        }
    }

    private fun search() {
        when(val instance = pages.value.items[pages.value.selectedIndex].instance) {
            is HubRootComponent.Pages.Bestiary -> {
                instance.component.onObtainEvent(
                    BestiaryComponent.Event.OnSearchCreature(state.value.query)
                )
            }
            is HubRootComponent.Pages.Classes -> {

            }
            is HubRootComponent.Pages.Items -> {

            }
            is HubRootComponent.Pages.Race -> {

            }
            is HubRootComponent.Pages.Spells -> {
                instance.component.onObtainEvent(
                    SpellComponent.Event.SearchSpell(state.value.query)
                )
            }
            null -> return
        }
    }

    @Serializable
    sealed interface Configuration {
        @Serializable data object Spells: Configuration
        @Serializable data object Items: Configuration
        @Serializable data object Classes: Configuration
        @Serializable data object Race: Configuration
        @Serializable
        data object Bestiary : Configuration
    }

    private companion object {
        private val pagesMap = persistentMapOf(
            SelectablePageUiModel(
                pageType = Page.ClASSES,
                isSelected = false,
            ) to Configuration.Classes,
            SelectablePageUiModel(
                pageType = Page.SPELL,
                isSelected = false,
            ) to Configuration.Spells,
            SelectablePageUiModel(
                pageType = Page.BESTIARY,
                isSelected = false,
            ) to Configuration.Bestiary,
//            SelectablePageUiModel(
//                pageType = Page.ITEM,
//                isSelected = false,
//            ) to Configuration.Items,
//            SelectablePageUiModel(
//                pageType = Page.RACE,
//                isSelected = false,
//            ) to Configuration.Race,
        )
    }
}