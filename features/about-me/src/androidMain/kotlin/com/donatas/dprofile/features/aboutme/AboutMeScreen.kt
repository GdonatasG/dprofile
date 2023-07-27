package com.donatas.dprofile.features.aboutme

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.donatas.dprofile.compose.components.layout.FlexibleAppBarScaffold
import com.donatas.dprofile.compose.components.layout.TabBar
import com.donatas.dprofile.feature.Components
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.experience.presentation.Tab
import com.donatas.dprofile.features.aboutme.presentation.AboutMeViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.getViewModel

actual class AboutMeScreen actual constructor() : Screen {
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Compose(components: Components) {
        val viewModel: AboutMeViewModel = getViewModel<AboutMeViewModel>()
        val navController: NavHostController = rememberAnimatedNavController()

        val listState = rememberLazyListState()

        FlexibleAppBarScaffold(
            listState = listState,
            title = "About Me",
            flexibleSpace = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    repeat(10) {
                        Text("header")
                    }
                }
            },
            flexibleSpaceHeight = 200.dp,
            tabBar = TabBar(
                height = 41.dp,
                content = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(modifier = Modifier.weight(1f)) {
                            Text(text = "dasds")
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .shadow(1.dp)
                        ) {
                            Box(modifier = Modifier)
                        }
                    }
                }
            )
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                repeat(
                    50
                ) { value ->
                    Text(
                        text = "Body$value",
                    )
                }
            }
        }

        /*Row(
            modifier = Modifier
                .height(50.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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
        }*/
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
