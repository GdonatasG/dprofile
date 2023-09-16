package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.navigation.core.ScreenFactory
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import org.koin.dsl.module

internal fun screenModule(factory: ScreenFactory) = module {
    single<ScreenFactory> {
        factory
    }

    single<BottomTabBarScreenFactory> {
        get<ScreenFactory>().bottomTabBar()
    }
}
