package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.navigation.core.ScreenFactory
import com.donatas.dprofile.composition.navigation.screens.AboutMeScreenFactory
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.EducationScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ExperienceScreenFactory
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory
import com.donatas.dprofile.composition.navigation.screens.RoadToProgrammingScreenFactory
import com.donatas.dprofile.composition.navigation.screens.SkillsScreenFactory
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

    single<EducationScreenFactory> {
        get<ScreenFactory>().education()
    }

    single<ExperienceScreenFactory> {
        get<ScreenFactory>().experience()
    }

    single<SkillsScreenFactory> {
        get<ScreenFactory>().skills()
    }

    single<RoadToProgrammingScreenFactory> {
        get<ScreenFactory>().roadToProgramming()
    }

    single<GithubScreenFactory> {
        get<ScreenFactory>().github()
    }

    single<ContactsScreenFactory> {
        get<ScreenFactory>().contacts()
    }
}
