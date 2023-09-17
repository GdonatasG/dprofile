package com.donatas.dprofile.composition.navigation.core

import com.donatas.dprofile.composition.navigation.screens.AboutMeScreenFactory
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.EducationScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ExperienceScreenFactory
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory
import com.donatas.dprofile.composition.navigation.screens.RoadToProgrammingScreenFactory
import com.donatas.dprofile.composition.navigation.screens.SkillsScreenFactory

interface ScreenFactory {
    fun bottomTabBar(): BottomTabBarScreenFactory

    fun aboutMe(): AboutMeScreenFactory
    fun education(): EducationScreenFactory
    fun experience(): ExperienceScreenFactory
    fun skills(): SkillsScreenFactory
    fun roadToProgramming(): RoadToProgrammingScreenFactory


    fun github(): GithubScreenFactory
    fun contacts(): ContactsScreenFactory
}
