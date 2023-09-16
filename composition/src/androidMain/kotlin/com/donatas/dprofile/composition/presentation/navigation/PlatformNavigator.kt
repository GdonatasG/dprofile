package com.donatas.dprofile.composition.presentation.navigation

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.composition.MainScreen
import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.composition.presentation.alert.AlertController
import com.donatas.dprofile.composition.presentation.screen.destinations.GithubSearchScreenDestination
import com.donatas.dprofile.composition.presentation.screen.destinations.MainScreenDestination
import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.search.GithubSearchScreen
import com.ramcosta.composedestinations.spec.Direction
import java.lang.RuntimeException

internal class PlatformNavigator(
    private val androidNavigator: AndroidNavigator,
    private val alertNavigator: AlertController
) : Navigator {
    private val backstack = mutableListOf<String>()

    override fun set(screen: Screen) {
        val direction = screen.toDestination()
        backstack.clear()
        backstack.add(direction.route)

        androidNavigator.navigate(
            NavigationAction(
                destination = direction,
                navOptions = AppNavOptions.Builder().setLaunchSingleTop(true).popUpAllInclusive().build()
            )
        )
    }

    override fun push(screen: Screen) {
        val direction = screen.toDestination()

        if (backstack.contains(direction.route)) return

        backstack.add(direction.route)

        androidNavigator.navigate(
            NavigationAction(
                destination = direction,
                navOptions = AppNavOptions.Builder().setLaunchSingleTop(true).build()
            )
        )
    }

    override fun replace(screen: Screen, previous: Int) {
        val direction = screen.toDestination()

        if (backstack.contains(direction.route))
            return

        (0 until previous).forEach { _ ->
            if (backstack.isEmpty()) return@forEach
            backstack.removeLast()
        }

        if (backstack.isEmpty())
            return push(screen = screen)

        val target = backstack.last()

        backstack.add(direction.route)

        androidNavigator.navigate(
            NavigationAction(
                destination = direction,
                navOptions = AppNavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .setPopUpTo(route = target, inclusive = false)
                    .build()
            )
        )
    }

    override fun show(alert: Alert) {
        alertNavigator.show(alert)
    }

    override fun push(modal: Modal) {
        androidNavigator.showModal(modal)
    }

    override fun openBrowser(url: String) {
        androidNavigator.navigateToUrl(url)
    }

    override fun pop() {
        androidNavigator.navigateBack()
    }

    override fun closeModal() {
        androidNavigator.closeModal()
    }
}

private fun Screen.toDestination(): Direction = when (this) {
    is MainScreen -> MainScreenDestination
    is GithubSearchScreen -> GithubSearchScreenDestination
    else -> throw RuntimeException("Destination not found!")
}
