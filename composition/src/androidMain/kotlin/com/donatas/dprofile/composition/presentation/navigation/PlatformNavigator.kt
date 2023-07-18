package com.donatas.dprofile.composition.presentation.navigation

import com.donatas.dprofile.composition.MainScreen
import com.donatas.dprofile.composition.navigation.Navigator
import com.donatas.dprofile.composition.presentation.screen.destinations.GithubSearchScreenDestination
import com.donatas.dprofile.composition.presentation.screen.destinations.MainScreenDestination
import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.search.GithubSearchScreen
import com.ramcosta.composedestinations.spec.Direction
import java.lang.RuntimeException

internal class PlatformNavigator(
    private val androidNavigator: AndroidNavigator
) : Navigator {
    override fun set(screen: Screen) {
        androidNavigator.navigate(
            NavigationAction(
                destination = screen.toDestination(),
                navOptions = AppNavOptions.Builder().setLaunchSingleTop(true).popUpAllInclusive().build()
            )
        )
    }

    override fun push(screen: Screen) {
        androidNavigator.navigate(
            NavigationAction(
                destination = screen.toDestination(),
                navOptions = AppNavOptions.Builder().setLaunchSingleTop(true).build()
            )
        )
    }

    override fun push(modal: Modal) {
        androidNavigator.showModal(modal)
    }

    override fun pop() {
        androidNavigator.navigateBack()
    }
}

private fun Screen.toDestination(): Direction = when (this) {
    is MainScreen -> MainScreenDestination
    is GithubSearchScreen -> GithubSearchScreenDestination
    else -> throw RuntimeException("Destination not found!")
}
