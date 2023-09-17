package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.navigation.core.ScreenFactory
import com.donatas.dprofile.composition.navigation.screens.AboutMeScreenFactory
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory
import org.koin.dsl.module

internal fun screenModule(factory: ScreenFactory) = module {
    single<ScreenFactory> {
        factory
    }

    single<BottomTabBarScreenFactory> {
        get<ScreenFactory>().bottomTabBar()
    }

    single<AboutMeScreenFactory> {
        get<ScreenFactory>().aboutMe()
    }

    single<GithubScreenFactory> {
        get<ScreenFactory>().github()
    }

    single<ContactsScreenFactory> {
        get<ScreenFactory>().contacts()
    }
}
