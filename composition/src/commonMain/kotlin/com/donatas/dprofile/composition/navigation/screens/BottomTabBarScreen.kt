package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.presentation.BottomTabBarController
import com.donatas.dprofile.feature.Screen

expect interface BottomTabBarScreenFactory

expect class BottomTabBarScreen constructor(
    factory: BottomTabBarScreenFactory,
    tabController: BottomTabBarController,
    appTutorial: AppTutorial
): Screen


