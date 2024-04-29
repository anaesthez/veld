package com.nesterov.veld.common.base

import com.arkivanov.decompose.Cancellation
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.rx.observer
import com.arkivanov.mvikotlin.core.store.Store

abstract class BaseComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    fun <T : Any> Store<*, T, *>.asValue(): Value<T> =
        object : Value<T>() {
            override val value: T get() = state

            override fun subscribe(observer: (T) -> Unit): Cancellation {
                val disposable = states(observer(onNext = observer))
                return Cancellation {
                    disposable.dispose()
                }
            }
        }
}