package com.donatas.dprofile.composition.navigation

import com.donatas.dprofile.composition.navigation.core.ScreenFactory
import com.donatas.dprofile.composition.navigation.screens.AboutMeScreenFactory
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.EducationScreenFactory
import com.donatas.dprofile.composition.navigation.screens.ExperienceScreenFactory
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory
import com.donatas.dprofile.composition.navigation.screens.GithubSearchScreenFactory
import com.donatas.dprofile.composition.navigation.screens.RoadToProgrammingScreenFactory
import com.donatas.dprofile.composition.navigation.screens.SkillsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.DefaultAboutMeScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.DefaultBottomTabBarScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.contacts.DefaultContactsScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.education.DefaultEducationScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.experience.DefaultExperienceScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.github.DefaultGithubScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.github_search.DefaultGithubSearchScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.road_to_programming.DefaultRoadToProgrammingScreenFactory
import com.donatas.dprofile.composition.navigation.screens.components.skills.DefaultSkillsScreenFactory

class ComposeScreenFactory : ScreenFactory {
    override fun bottomTabBar(): BottomTabBarScreenFactory {
        return DefaultBottomTabBarScreenFactory()
    }

    override fun aboutMe(): AboutMeScreenFactory {
        return DefaultAboutMeScreenFactory()
    }

    override fun education(): EducationScreenFactory {
        return DefaultEducationScreenFactory()
    }

    override fun experience(): ExperienceScreenFactory {
        return DefaultExperienceScreenFactory()
    }

    override fun skills(): SkillsScreenFactory {
        return DefaultSkillsScreenFactory()
    }

    override fun roadToProgramming(): RoadToProgrammingScreenFactory {
        return DefaultRoadToProgrammingScreenFactory()
    }

    override fun github(): GithubScreenFactory {
        return DefaultGithubScreenFactory()
    }

    override fun githubSearch(): GithubSearchScreenFactory {
        return DefaultGithubSearchScreenFactory()
    }

    override fun contacts(): ContactsScreenFactory {
        return DefaultContactsScreenFactory()
    }
}
