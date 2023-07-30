package com.donatas.dprofile.features.aboutme

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.donatas.dprofile.compose.components.animation.EnterAnimation
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.compose.components.tab.DLazyTabRow
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.education.EducationFeature
import com.donatas.dprofile.features.aboutme.experience.ExperienceFeature
import com.donatas.dprofile.features.aboutme.presentation.AboutMeViewModel
import com.donatas.dprofile.features.aboutme.presentation.Tab
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingFeature
import com.donatas.dprofile.features.aboutme.skills.SkillsFeature
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

private fun Tab.toInt(): Int = when (this) {
    Tab.EXPERIENCE -> 0
    Tab.EDUCATION -> 1
    Tab.SKILLS -> 2
    Tab.ROAD_TO_PROGRAMMING -> 3
}

private fun Int.toTab(): Tab = when (this) {
    0 -> Tab.EXPERIENCE
    1 -> Tab.EDUCATION
    2 -> Tab.SKILLS
    3 -> Tab.ROAD_TO_PROGRAMMING
    else -> throw RuntimeException("Tab not implemented")
}

actual class AboutMeScreen actual constructor() : Screen {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Compose() {
        val viewModel: AboutMeViewModel = getViewModel<AboutMeViewModel>()
        val navController: NavHostController = rememberAnimatedNavController()

        val selectedTab by viewModel.selectedTab.collectAsState()

        LaunchedEffect(selectedTab) {
            navController.changeTab(selectedTab)
        }

        AppScaffold(
            appBar = {
                DAppBar(title = "About me")
            },
            tabBar = {
                DLazyTabRow(
                    selectedIndex = selectedTab.toInt(),
                    items = listOf(
                        "Experience",
                        "Education",
                        "Skills",
                        "Road to programming"
                    ),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 8.dp),
                    onTabClick = { index ->
                        val newTab: Tab = index.toTab()
                        viewModel.setTab(newTab)
                    }
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Tab.EXPERIENCE.route
            ) {
                composable(Tab.EXPERIENCE.route) {
                    EnterAnimation {
                        get<ExperienceFeature>().screen().Compose()
                    }
                }
                composable(Tab.EDUCATION.route) {
                    EnterAnimation {
                        get<EducationFeature>().screen().Compose()
                    }
                }
                composable(Tab.SKILLS.route) {
                    EnterAnimation {
                        get<SkillsFeature>().screen().Compose()
                    }
                }
                composable(Tab.ROAD_TO_PROGRAMMING.route) {
                    EnterAnimation {
                        get<RoadToProgrammingFeature>().screen().Compose()
                    }
                }
            }
        }
    }

    private fun NavHostController.changeTab(tab: Tab) {
        this.popBackStack()
        this.navigate(tab.route) {
            launchSingleTop = true
            restoreState = true
            popUpTo(this@changeTab.graph.findStartDestination().route!!) {
                inclusive = true
            }
        }
    }
}
