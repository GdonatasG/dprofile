package com.donatas.dprofile.composition.navigation.screens.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.compose.components.tab.DLazyTabRow
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
    override fun Compose(viewModel: AboutMeViewModel) {
        val navController: NavHostController = rememberAnimatedNavController()

        val selectedTab by viewModel.selectedTab.collectAsState()
        AppScaffold(appBar = {
            DAppBar(title = "About me")
        }, tabBar = {
            DLazyTabRow(selectedIndex = viewModel.tabs.indexOf(selectedTab),
                items = viewModel.tabs.map { it.type.title },
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 8.dp),
                onTabClick = Click@{ index ->
                    val newTab: AboutMeTab = viewModel.tabs[index]

                    if (selectedTab.type == newTab.type) return@Click

                    viewModel.select(newTab)
                    navController.changeTab(selectedTab)
                })
        }) {
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
