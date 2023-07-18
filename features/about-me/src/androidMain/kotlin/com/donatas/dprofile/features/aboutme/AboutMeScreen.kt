package com.donatas.dprofile.features.aboutme

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.donatas.dprofile.compose.components.animation.EnterAnimation
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.education.EducationFeature
import com.donatas.dprofile.features.aboutme.experience.ExperienceFeature
import com.donatas.dprofile.features.aboutme.experience.presentation.Tab
import com.donatas.dprofile.features.aboutme.presentation.AboutMeViewModel
import com.donatas.dprofile.features.aboutme.skills.SkillsFeature
import com.donatas.dprofile.features.aboutme.ui.AboutMeView
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

actual class AboutMeScreen actual constructor() : Screen {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Compose() {
        val viewModel: AboutMeViewModel = getViewModel<AboutMeViewModel>()
        val navController: NavHostController = rememberAnimatedNavController()

        AppScaffold(tabBar = {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ElevatedButton(onClick = {
                    navController.changeTab(Tab.EXPERIENCE)
                }) {
                    Text(text = "Exp")
                }
                ElevatedButton(onClick = {
                    navController.changeTab(Tab.EDUCATION)
                }) {
                    Text(text = "Edu")
                }
                ElevatedButton(onClick = {
                    navController.changeTab(Tab.SKILLS)
                }) {
                    Text(text = "Skills")
                }
                ElevatedButton(onClick = {
                    navController.changeTab(Tab.ROAD_TO_PROGRAMMING)
                }) {
                    Text(text = "Road")
                }
            }
        }) {
            NavHost(navController = navController, startDestination = Tab.EXPERIENCE.route) {
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
