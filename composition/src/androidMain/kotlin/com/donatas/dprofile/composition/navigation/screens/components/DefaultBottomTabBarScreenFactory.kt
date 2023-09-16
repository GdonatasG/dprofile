package com.donatas.dprofile.composition.navigation.screens.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.composition.presentation.navigation.BottomNavBar
import com.donatas.dprofile.composition.presentation.navigation.MainScreenBottomNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class DefaultBottomTabBarScreenFactory : BottomTabBarScreenFactory {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Compose(tabController: BottomTabBarController) {
        val selectedTab by tabController.selectedTab.collectAsState()

        val bottomNavController = rememberAnimatedNavController()

        Scaffold(
            modifier = Modifier,
            bottomBar = {
                BottomNavBar(
                    navController = bottomNavController,
                    tabs = tabController.tabs,
                    onSelect = tabController::select
                )
            }
        ) {
            Box(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                MainScreenBottomNavigation(
                    bottomNavController = bottomNavController,
                    screen = {
                        selectedTab.factory().Compose()
                    },
                    onActivityDestroyed = tabController::onFinish
                )
            }
        }
    }
}
