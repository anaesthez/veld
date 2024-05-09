package com.nesterov.veld.di.graph

fun <T> provideDependencies(block: DependenciesScope.() -> T): T {
    return Dependencies.block()
}