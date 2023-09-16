package com.donatas.dprofile.composition.navigation.core

import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory

interface ScreenFactory {
    fun bottomTabBar(): BottomTabBarScreenFactory
}
