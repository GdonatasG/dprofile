package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.feature.Screen
import platform.UIKit.UIViewController

actual interface BottomTabBarScreenFactory {
    fun controller(tabController: BottomTabBarController, appTutorial: AppTutorial): UIViewController
}

actual class BottomTabBarScreen actual constructor(
    private val factory: BottomTabBarScreenFactory,
    private val tabController: BottomTabBarController,
    private val appTutorial: AppTutorial
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(tabController = tabController, appTutorial = appTutorial)
    }
}
