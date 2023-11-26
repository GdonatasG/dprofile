package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.feature.Screen

actual interface BottomTabBarScreenFactory {
    @Composable
    fun Compose(tabController: BottomTabBarController, appTutorial: AppTutorial)
}

actual class BottomTabBarScreen actual constructor(
    private val factory: BottomTabBarScreenFactory,
    private val tabController: BottomTabBarController,
    private val appTutorial: AppTutorial
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(tabController = tabController, appTutorial = appTutorial)
    }
}
