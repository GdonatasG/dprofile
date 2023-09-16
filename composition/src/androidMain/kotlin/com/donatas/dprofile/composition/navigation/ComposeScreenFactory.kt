package com.donatas.dprofile.composition.navigation

import com.donatas.dprofile.composition.navigation.core.ScreenFactory
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.DefaultBottomTabBarScreenFactory

class ComposeScreenFactory : ScreenFactory {
    override fun bottomTabBar(): BottomTabBarScreenFactory {
        return DefaultBottomTabBarScreenFactory()
    }
}
