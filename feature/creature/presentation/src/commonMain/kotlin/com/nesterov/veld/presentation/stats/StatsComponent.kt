package com.nesterov.veld.presentation.stats

import com.arkivanov.decompose.ComponentContext
import com.nesterov.veld.common.base.BaseComponent

interface StatsComponent {

}

class StatsComponentImpl(
    componentContext: ComponentContext,
) : BaseComponent(componentContext), StatsComponent {

}