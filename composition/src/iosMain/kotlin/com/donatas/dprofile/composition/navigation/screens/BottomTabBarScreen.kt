package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.feature.Screen
import platform.UIKit.UIViewController

actual interface BottomTabBarScreenFactory {
    fun controller(tabController: BottomTabBarController): UIViewController
}

actual class BottomTabBarScreen actual constructor(
    private val factory: BottomTabBarScreenFactory,
    private val tabController: BottomTabBarController
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(tabController = tabController)
    }
}
