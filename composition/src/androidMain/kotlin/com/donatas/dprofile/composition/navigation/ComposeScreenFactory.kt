package com.donatas.dprofile.composition.navigation

import com.donatas.dprofile.composition.navigation.core.ScreenFactory
import com.donatas.dprofile.composition.navigation.screens.AboutMeScreenFactory
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.DefaultAboutMeScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.DefaultBottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.contacts.DefaultContactsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.github.DefaultGithubScreenFactory

class ComposeScreenFactory : ScreenFactory {
    override fun bottomTabBar(): BottomTabBarScreenFactory {
        return DefaultBottomTabBarScreenFactory()
    }

    override fun aboutMe(): AboutMeScreenFactory {
       return DefaultAboutMeScreenFactory()
    }

    override fun github(): GithubScreenFactory {
        return DefaultGithubScreenFactory()
    }

    override fun contacts(): ContactsScreenFactory {
        return DefaultContactsScreenFactory()
    }
}
