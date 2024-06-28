package com.nesterov.veld.presentation.creature

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.children.ChildNavState
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.Pages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.pages.childPages
import com.arkivanov.decompose.router.pages.select
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.nesterov.veld.common.base.BaseComponent
import com.nesterov.veld.presentation.actions.ActionsComponent
import com.nesterov.veld.presentation.actions.ActionsComponentImpl
import com.nesterov.veld.presentation.creature.di.CreatureDependencies
import com.nesterov.veld.presentation.info.InfoComponent
import com.nesterov.veld.presentation.info.InfoComponentImpl
import com.nesterov.veld.presentation.stats.StatsComponent
import com.nesterov.veld.presentation.stats.StatsComponentImpl
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@OptIn(ExperimentalDecomposeApi::class)
interface CreatureComponent {
    val state: Value<CreatureStore.State>
    val pages: Value<ChildPages<*, Pages>>
    fun onObtainEvent(event: Event)

    sealed interface Pages {
        data class Info(val component: InfoComponent) : Pages
        data class Stats(val component: StatsComponent) : Pages
        data class Actions(val component: ActionsComponent) : Pages
    }

    sealed interface Event {
        data object OnRetryClick : Event
        data object OnBackClick : Event
        data class OnPageSelected(val index: Int) : Event
        data class OnSearchCreature(val query: String) : Event
    }

    sealed interface Action {
        data object OnBackClick : Action
    }
}

@OptIn(ExperimentalDecomposeApi::class)
class CreatureComponentImpl(
    storeFactory: StoreFactory,
    index: String,
    dependencies: CreatureDependencies,
    componentContext: ComponentContext,
    private val action: (CreatureComponent.Action) -> Unit,
) : BaseComponent(componentContext), CreatureComponent {
    private val pagesNavigation = PagesNavigation<Configuration>()
    override val pages: Value<ChildPages<Configuration, CreatureComponent.Pages>> =
        childPages(
            source = pagesNavigation,
            serializer = serializer<Configuration>(),
            pageStatus = ::creaturePagesStatus,
            initialPages = ::initialPages,
            childFactory = ::childFactory
        )
    private val bestiaryStore = instanceKeeper.getStore {
        CreatureStoreFactory(
            storeFactory = storeFactory,
            dependencies = dependencies,
            index = index,
        ).create()
    }
    override val state: Value<CreatureStore.State> = bestiaryStore.asValue()

    private fun creaturePagesStatus(index: Int, pages: Pages<*>): ChildNavState.Status =
        when (index) {
            pages.selectedIndex -> ChildNavState.Status.RESUMED
            else -> ChildNavState.Status.CREATED
        }

    private fun initialPages() = Pages(
        items = listOf(Configuration.Actions, Configuration.Stats, Configuration.Info),
        selectedIndex = 1,
    )

    private fun childFactory(page: Configuration, ctx: ComponentContext): CreatureComponent.Pages =
        when (page) {
            Configuration.Actions -> CreatureComponent.Pages.Actions(
                ActionsComponentImpl(
                    componentContext = ctx,
                )
            )

            Configuration.Info -> CreatureComponent.Pages.Info(
                InfoComponentImpl(
                    componentContext = ctx,
                )
            )

            Configuration.Stats -> CreatureComponent.Pages.Stats(
                StatsComponentImpl(
                    componentContext = ctx,
                )
            )
        }

    override fun onObtainEvent(event: CreatureComponent.Event) =
        when (event) {
            CreatureComponent.Event.OnBackClick -> {
                action(CreatureComponent.Action.OnBackClick)
            }

            CreatureComponent.Event.OnRetryClick -> {

            }

            is CreatureComponent.Event.OnSearchCreature -> {
                bestiaryStore.accept(CreatureStore.Intent.OnSearchCreature(event.query))
            }

            is CreatureComponent.Event.OnPageSelected -> {
                pagesNavigation.select(event.index)
            }
        }

    @Serializable
    sealed interface Configuration {
        @Serializable
        data object Info : Configuration
        @Serializable
        data object Stats : Configuration
        @Serializable
        data object Actions : Configuration
    }
}
