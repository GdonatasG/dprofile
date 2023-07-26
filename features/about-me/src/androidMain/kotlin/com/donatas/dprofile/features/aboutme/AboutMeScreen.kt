package com.donatas.dprofile.features.aboutme

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.donatas.dprofile.compose.components.appbar.FlexibleAppBarScaffold
import com.donatas.dprofile.compose.components.appbar.TabBar
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
                height = 40.dp,
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue)
                    ) {
                        Text(text = "dasds")
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


        /* Surface(
             modifier = Modifier
                 .background(color = MaterialTheme.colorScheme.background)
                 .statusBarsPadding(),
             color = MaterialTheme.colorScheme.background,
             contentColor = contentColorFor(MaterialTheme.colorScheme.background)
         ) {
             *//*Box(modifier = Modifier.fillMaxSize()) {
                Header(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight),
                    scroll = listState,
                    headerHeightPx = headerHeightPx,
                )
                Body(
                    scroll = listState,
                )
                Toolbar(
                    scroll = listState,
                    headerHeightPx = headerHeightPx,
                    toolbarHeightPx = appBarHeightPx
                )
                Column {
                    Spacer(modifier = Modifier.height(if (showToolbar) toolbarHeight else headerHeight))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .graphicsLayer {
                                if (showToolbar) return@graphicsLayer
                                val scrollOffset = listState.calculateScrollOffset(headerHeightPx)
                                translationY = -scrollOffset // Parallax effect
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Blue)
                        ) {
                            Text(text = "dasds")
                        }
                    }

                }
                *//**//* Row(
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
                 }*//**//*
            }*//*
            *//* Row(
                 modifier = Modifier.background(MaterialTheme.colorScheme.background),
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
             }*//*
            *//*LazyColumn(state = listState) {
                item {
                    Spacer(Modifier.height(calculatedHeight))
                    NavHost(navController = navController, startDestination = Tab.EXPERIENCE.route) {
                        composable(Tab.EXPERIENCE.route) {
                            EnterAnimation {
                                get<ExperienceFeature>().screen().Compose(
                                    components = Components(
                                        lazyListState = listState
                                    )
                                )
                            }
                        }
                        composable(Tab.EDUCATION.route) {
                            EnterAnimation {
                                get<EducationFeature>().screen().Compose(components = Components())
                            }
                        }
                        composable(Tab.SKILLS.route) {
                            EnterAnimation {
                                get<SkillsFeature>().screen().Compose(components = Components())
                            }
                        }
                        composable(Tab.ROAD_TO_PROGRAMMING.route) {
                            EnterAnimation {
                                get<RoadToProgrammingFeature>().screen().Compose(components = Components())
                            }
                        }
                    }
                }
            }*//*
        }*/
    }


    /*AppScaffoldWithCollapsibleHeader(
        lazyListState = listState,
        appBar = {
            DAppBar(
                title = "About Me"
            )
        },
        header = header,
    ) {
        NavHost(navController = navController, startDestination = Tab.EXPERIENCE.route) {
            composable(Tab.EXPERIENCE.route) {
                EnterAnimation {
                    get<ExperienceFeature>().screen().Compose(
                        components = Components(
                            lazyListState = listState
                        )
                    )
                }
            }
            composable(Tab.EDUCATION.route) {
                EnterAnimation {
                    get<EducationFeature>().screen().Compose(components = Components())
                }
            }
            composable(Tab.SKILLS.route) {
                EnterAnimation {
                    get<SkillsFeature>().screen().Compose(components = Components())
                }
            }
            composable(Tab.ROAD_TO_PROGRAMMING.route) {
                EnterAnimation {
                    get<RoadToProgrammingFeature>().screen().Compose(components = Components())
                }
            }
        }
    }*/

    /*AppScaffold(appBar = {
        DAppBar(
            title = "About Me", headerHeight = headerHeight, scrollState = scrollState
        )
    }, header = header, tabBar = {
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
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
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
                        get<RoadToProgrammingFeature>().screen().Compose()
                    }
                }
            }
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
