package com.donatas.dprofile.composition.navigation.screens.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.compose.components.tab.DLazyTabRow
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.components.Message
import com.donatas.dprofile.composition.navigation.screens.AboutMeScreenFactory
import com.donatas.dprofile.features.aboutme.AboutMeTab
import com.donatas.dprofile.features.aboutme.AboutMeViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

private val AboutMeTab.Type.route: String
    get() = when (this) {
        AboutMeTab.Type.EXPERIENCE -> "experience"
        AboutMeTab.Type.EDUCATION -> "education"
        AboutMeTab.Type.SKILLS -> "skills"
        AboutMeTab.Type.ROAD_TO_PROGRAMMING -> "road_to_programming"
    }

private val AboutMeTab.Type.title: String
    get() = when (this) {
        AboutMeTab.Type.EXPERIENCE -> "Experience"
        AboutMeTab.Type.EDUCATION -> "Education"
        AboutMeTab.Type.SKILLS -> "Skills"
        AboutMeTab.Type.ROAD_TO_PROGRAMMING -> "Road to programming"
    }

class DefaultAboutMeScreenFactory : AboutMeScreenFactory {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Compose(viewModel: AboutMeViewModel, appTutorial: AppTutorial) {
        val navController: NavHostController = rememberAnimatedNavController()

        val tutorialState by appTutorial.state.collectAsState()
        val selectedTab by viewModel.selectedTab.collectAsState()

        LaunchedEffect(tutorialState) {
            val tab: AboutMeTab? = viewModel.tabs.firstOrNull {
                it.type == when (tutorialState.step) {
                    2 -> AboutMeTab.Type.EXPERIENCE
                    3 -> AboutMeTab.Type.EDUCATION
                    4 -> AboutMeTab.Type.SKILLS
                    5 -> AboutMeTab.Type.ROAD_TO_PROGRAMMING
                    else -> null
                }
            }

            tab?.let {
                viewModel.select(it)
                navController.changeTab(it)
            }
        }


        AppScaffold(appBar = {
            DAppBar(title = "About me")
        }, tabBar = {
            if (tutorialState.isFinished || tutorialState.step > 1) {
                DLazyTabRow(selectedIndex = viewModel.tabs.indexOf(selectedTab),
                    items = viewModel.tabs.filter {
                        if (tutorialState.isFinished) true else
                            when (it.type) {
                                AboutMeTab.Type.EXPERIENCE -> tutorialState.step >= 2
                                AboutMeTab.Type.EDUCATION -> tutorialState.step >= 3
                                AboutMeTab.Type.SKILLS -> tutorialState.step >= 4
                                AboutMeTab.Type.ROAD_TO_PROGRAMMING -> tutorialState.step >= 5
                            }
                    }.map { it.type.title },
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    onTabClick = Click@{ index ->
                        val newTab: AboutMeTab = viewModel.tabs[index]
                        if (selectedTab.type == newTab.type) return@Click
                        if (!tutorialState.isFinished) {
                            appTutorial.setStepManually(
                                when (newTab.type) {
                                    AboutMeTab.Type.EXPERIENCE -> 2
                                    AboutMeTab.Type.EDUCATION -> 3
                                    AboutMeTab.Type.SKILLS -> 4
                                    AboutMeTab.Type.ROAD_TO_PROGRAMMING -> 5
                                }
                            )

                            return@Click
                        }

                        viewModel.select(newTab)
                        navController.changeTab(newTab)
                    })
            }
        }) {
            if (tutorialState.isFinished || tutorialState.step > 1) {
                NavHost(
                    navController = navController, startDestination = AboutMeTab.Type.EXPERIENCE.route
                ) {
                    composable(AboutMeTab.Type.EXPERIENCE.route) {
                        selectedTab.factory().Compose()
                    }
                    composable(AboutMeTab.Type.EDUCATION.route) {
                        selectedTab.factory().Compose()
                    }
                    composable(AboutMeTab.Type.SKILLS.route) {
                        selectedTab.factory().Compose()
                    }
                    composable(AboutMeTab.Type.ROAD_TO_PROGRAMMING.route) {
                        selectedTab.factory().Compose()
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
                ) {
                    Message(message = "Hi! I'm Donatas, mobile applications developer.\n\n" +
                            "Nice to meet you and thank you for downloading my profile (virtual CV) application. " +
                            "In the following steps, you will find an introduction about me, where you will be able to get to know me better.\n\n" +
                            "Have a good journey!")
                }
            }

        }
    }

    private fun NavHostController.changeTab(tab: AboutMeTab) {
        this.popBackStack()
        this.navigate(tab.type.route) {
            launchSingleTop = true
            restoreState = true
            popUpTo(this@changeTab.graph.findStartDestination().route!!) {
                inclusive = true
            }
        }
    }
}
