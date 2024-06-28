package com.nesterov.veld.di.context

import android.content.Context
import java.lang.ref.WeakReference

object AppContext {
    private var value: WeakReference<Context?>? = null

    fun set(context: Context) {
        value = WeakReference(context)
    }

    fun get(): Context? {
        return value?.get()
    }
}