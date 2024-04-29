package ru.nesterov.veld.hub.store

import androidx.compose.ui.util.fastMap
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import ru.nesterov.veld.hub.model.SelectablePageUiModel
import com.nesterov.veld.graph.hub.store.HubStore
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

class HubStoreFactory(
    private val storeFactory: StoreFactory,
    private val initialPages: ImmutableList<SelectablePageUiModel>,
) {
    @OptIn(ExperimentalMviKotlinApi::class)
    fun create(): HubStore =
        object : HubStore, Store<HubStore.Intent, HubStore.State, HubStore.Label>
        by storeFactory.create(
            name = STORE_NAME,
            initialState = HubStore.State(
                pages = initialPages,
                query = INITIAL_QUERY,
            ),
            bootstrapper = reaktiveBootstrapper {

            },
            executorFactory = reaktiveExecutorFactory {
                onIntent<HubStore.Intent.SelectPage> {
                    dispatch(Msg.SelectPage(it.index))
                }
            },
            reducer = ReducerImpl,
        ) { }

    private sealed interface Msg {
        data class SelectPage(val index: Int): Msg
    }

    private object ReducerImpl : Reducer<HubStore.State, Msg> {
        override fun HubStore.State.reduce(msg: Msg): HubStore.State =
            when(msg) {
                is Msg.SelectPage -> {
                    copy(
                        pages = pages
                            .fastMap {
                                if (it.pageType.ordinal == msg.index) {
                                    it.copy(isSelected = true)
                                } else {
                                    it.copy(isSelected = false)
                                }
                            }
                            .toImmutableList()
                    )
                }
            }
    }

    private companion object {
        private const val STORE_NAME = "HubStore"
        private const val INITIAL_QUERY = ""
    }
}