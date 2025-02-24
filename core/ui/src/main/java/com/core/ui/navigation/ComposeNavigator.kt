package com.core.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ComposeNavigator : Navigator {

    private val commandFlow: MutableStateFlow<NavigationCommand> =
        MutableStateFlow(NavigationCommand.Idle)

    override val commands
        get() = commandFlow.asStateFlow()

    override fun navigate(command: NavigationCommand) {
        commandFlow.update { command }
    }

    override fun onNavigated() {
        commandFlow.update { NavigationCommand.Idle }
    }
}
