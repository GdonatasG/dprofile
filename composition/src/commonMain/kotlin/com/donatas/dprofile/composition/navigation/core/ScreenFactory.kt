package com.donatas.dprofile.composition.navigation.core

import com.donatas.dprofile.composition.navigation.screens.AboutMeScreenFactory
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory

interface ScreenFactory {
    fun bottomTabBar(): BottomTabBarScreenFactory
    fun aboutMe(): AboutMeScreenFactory
    fun github(): GithubScreenFactory
    fun contacts(): ContactsScreenFactory
}
