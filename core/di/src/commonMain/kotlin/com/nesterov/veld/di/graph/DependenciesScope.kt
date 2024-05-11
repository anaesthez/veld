package com.nesterov.veld.di.graph

import com.nesterov.veld.presentation.di.BestiaryDependencies
import com.nesterov.veld.presentation.di.ClassDetailsDependencies
import com.nesterov.veld.presentation.di.SpellDependencies
import com.nesterov.veld.presentation.di.SpellDetailsDependencies

interface DependenciesScope {
    val spellDependencies: SpellDependencies
    val spellDetailsDependencies: SpellDetailsDependencies
    val classDetailsDependencies: ClassDetailsDependencies
    val bestiaryDependencies: BestiaryDependencies
}