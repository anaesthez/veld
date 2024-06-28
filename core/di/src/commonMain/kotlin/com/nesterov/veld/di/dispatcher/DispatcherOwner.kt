package com.nesterov.veld.di.dispatcher

import com.nesterov.veld.common.AppDispatcher

interface DispatcherOwner {
    val appDispatcher: AppDispatcher
}

object DispatcherOwnerInstance : DispatcherOwner {
    override val appDispatcher: AppDispatcher = AppDispatcherImpl
}

fun <T> provideDispatcher(block: DispatcherOwner.() -> T): T {
    return DispatcherOwnerInstance.block()
}
