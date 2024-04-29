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
import com.nesterov.veld.di.graph.AppDependenciesGraph
import ru.nesterov.veld.hub.model.Page
import ru.nesterov.veld.hub.model.SelectablePageUiModel
import ru.nesterov.veld.hub.store.HubStore
import ru.nesterov.veld.hub.store.HubStoreFactory
import com.nesterov.veld.presentation.BackstoryComponent
import com.nesterov.veld.presentation.BackstoryComponentImpl
import com.nesterov.veld.presentation.ClassesComponent
import com.nesterov.veld.presentation.ClassesComponentImpl
import com.nesterov.veld.presentation.ItemComponent
import com.nesterov.veld.presentation.ItemComponentImpl
import com.nesterov.veld.presentation.RaceComponent
import com.nesterov.veld.presentation.RaceComponentImpl
import com.nesterov.veld.presentation.SpellComponent
import com.nesterov.veld.presentation.SpellComponentImpl
import com.nesterov.veld.presentation.model.SpellPresentationModel
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

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
        data class Backstory(val component: BackstoryComponent): Pages
    }

    sealed interface Action {
        data class NavigateSpellDetails(val spellIndex: String): Action
    }
}

@OptIn(ExperimentalDecomposeApi::class)
class HubRootComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val appDependenciesGraph: AppDependenciesGraph,
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
        selectedIndex = INITIAL_SELECTED_INDEX,
    )

    private fun childFactory(page: Configuration, ctx: ComponentContext): HubRootComponent.Pages =
        when (page) {
            Configuration.Classes -> HubRootComponent.Pages.Classes(
                ClassesComponentImpl(
                    componentContext = ctx,
                    storeFactory = storeFactory,
                )
            )

            Configuration.Spells -> HubRootComponent.Pages.Spells(
                SpellComponentImpl(
                    componentContext = ctx,
                    storeFactory = storeFactory,
                    dependencies = appDependenciesGraph.spellDependencies,
                    onAction = ::onSpellAction,
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

            Configuration.Backstory -> HubRootComponent.Pages.Backstory(
                BackstoryComponentImpl(
                    componentContext = ctx,
                    storeFactory = storeFactory,
                )
            )
        }

    private fun onSpellAction(action: SpellComponent.Action) =
        when (action) {
            is SpellComponent.Action.NavigateSpellDetails -> onAction(
                HubRootComponent.Action.NavigateSpellDetails(action.index)
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
            is HubRootComponent.Pages.Backstory -> {

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
        @Serializable data object Backstory: Configuration
    }

    private companion object {
        private const val INITIAL_SELECTED_INDEX = 0
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
                pageType = Page.ITEM,
                isSelected = false,
            ) to Configuration.Items,
            SelectablePageUiModel(
                pageType = Page.RACE,
                isSelected = false,
            ) to Configuration.Race,
            SelectablePageUiModel(
                pageType = Page.BACKSTORY,
                isSelected = false,
            ) to Configuration.Backstory,
        )
    }
}