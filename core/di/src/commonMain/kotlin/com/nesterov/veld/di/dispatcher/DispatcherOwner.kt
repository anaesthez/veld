package com.nesterov.veld.di.dispatcher

import com.nesterov.veld.common.AppDispatcher

interface DispatcherOwner {
    val appDispatcher: AppDispatcher
}

fun <T> AppDispatcher.provideDispatcher(block: DispatcherOwner.() -> T): T {
    return object : DispatcherOwner {
        override val appDispatcher: AppDispatcher = this@provideDispatcher
    }.block()
}