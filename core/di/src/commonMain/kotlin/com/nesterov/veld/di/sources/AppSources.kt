package com.nesterov.veld.di.sources

import com.nesterov.veld.di.sources.local.LocalSources
import com.nesterov.veld.di.sources.local.LocalSourcesImpl
import com.nesterov.veld.di.sources.network.RemoteSources
import com.nesterov.veld.di.sources.network.RemoteSourcesImpl

interface AppSources {
    val localSources: LocalSources
        get() = LocalSourcesImpl
    val remoteSources: RemoteSources
        get() = RemoteSourcesImpl
}