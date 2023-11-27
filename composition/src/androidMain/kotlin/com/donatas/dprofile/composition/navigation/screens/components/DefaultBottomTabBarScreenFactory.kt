package com.donatas.dprofile.composition.navigation.screens.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.presentation.BottomTab
import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.composition.presentation.navigation.BottomNavBar
import com.donatas.dprofile.composition.presentation.navigation.MainScreenBottomNavigation
import com.donatas.dprofile.composition.presentation.navigation.route
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class DefaultBottomTabBarScreenFactory : BottomTabBarScreenFactory {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Compose(tabController: BottomTabBarController, appTutorial: AppTutorial) {
        val selectedTab by tabController.selectedTab.collectAsState()
        val tutorialState by appTutorial.state.collectAsState()

        val bottomNavController = rememberAnimatedNavController()

        LaunchedEffect(tutorialState) {
            if (tutorialState.isFinished) return@LaunchedEffect

            if (tutorialState.step in 1..5) {
                if (bottomNavController.currentBackStackEntry?.destination?.route == BottomTab.Type.ABOUT_ME.route) return@LaunchedEffect

                tabController.select(tabController.tabs.first { it.type == BottomTab.Type.ABOUT_ME })
                bottomNavController.navigate(BottomTab.Type.ABOUT_ME.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(bottomNavController.graph.findStartDestination().route!!) {
                        saveState = true
                    }
                }
                return@LaunchedEffect
            }

            if (tutorialState.step in 6..7) {
                if (bottomNavController.currentBackStackEntry?.destination?.route == BottomTab.Type.GITHUB.route) return@LaunchedEffect

                tabController.select(tabController.tabs.first { it.type == BottomTab.Type.GITHUB })
                bottomNavController.navigate(BottomTab.Type.GITHUB.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(bottomNavController.graph.findStartDestination().route!!) {
                        saveState = true
                    }
                }
                return@LaunchedEffect
            }

            if (tutorialState.step in 8..9) {
                if (bottomNavController.currentBackStackEntry?.destination?.route == BottomTab.Type.CONTACTS.route) return@LaunchedEffect

                tabController.select(tabController.tabs.first { it.type == BottomTab.Type.CONTACTS })
                bottomNavController.navigate(BottomTab.Type.CONTACTS.route) {
                    launchSingleTop = true
                    restoreState = true
                    popUpTo(bottomNavController.graph.findStartDestination().route!!) {
                        saveState = true
                    }
                }

                return@LaunchedEffect
            }
        }

        Scaffold(modifier = Modifier, bottomBar = {
            BottomNavBar(
                navController = bottomNavController,
                tabs = tabController.tabs,
                onSelect = tabController::select,
                appTutorial = appTutorial
            )
        }) {
            Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                MainScreenBottomNavigation(bottomNavController = bottomNavController,
                    appTutorial = appTutorial,
                    screen = {
                        selectedTab.factory().Compose()
                    },
                    onActivityDestroyed = tabController::onFinish,
                    tabs = tabController.tabs,
                    onChanged = { tab ->
                        tabController.select(tab)
                    })
            }
        }
    }
}
