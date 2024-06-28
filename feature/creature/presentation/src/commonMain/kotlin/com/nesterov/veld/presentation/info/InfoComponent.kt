package com.nesterov.veld.presentation.info

import com.arkivanov.decompose.ComponentContext
import com.nesterov.veld.common.base.BaseComponent

interface InfoComponent {

}

class InfoComponentImpl(
    componentContext: ComponentContext,
) : BaseComponent(componentContext), InfoComponent {

}