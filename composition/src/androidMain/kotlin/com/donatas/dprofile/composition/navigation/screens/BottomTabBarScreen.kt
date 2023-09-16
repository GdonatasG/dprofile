package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.feature.Screen

actual interface BottomTabBarScreenFactory {
    @Composable
    fun Compose(tabController: BottomTabBarController)
}

actual class BottomTabBarScreen actual constructor(
    private val factory: BottomTabBarScreenFactory, private val tabController: BottomTabBarController
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(tabController = tabController)
    }
}
