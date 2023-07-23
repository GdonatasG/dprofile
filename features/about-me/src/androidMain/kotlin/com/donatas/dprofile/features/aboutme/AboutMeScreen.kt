package com.donatas.dprofile.features.aboutme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.donatas.dprofile.compose.components.animation.EnterAnimation
import com.donatas.dprofile.compose.components.appbar.AppBarHeader
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.extension.calculateScrollOffset
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.feature.Components
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.education.EducationFeature
import com.donatas.dprofile.features.aboutme.experience.ExperienceFeature
import com.donatas.dprofile.features.aboutme.experience.presentation.Tab
import com.donatas.dprofile.features.aboutme.presentation.AboutMeViewModel
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingFeature
import com.donatas.dprofile.features.aboutme.skills.SkillsFeature
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

private val headerHeight = 250.dp
private val toolbarHeight = 60.dp

@Composable
private fun Header(
    scroll: LazyListState,
    headerHeightPx: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                val scrollOffset = scroll.calculateScrollOffset(headerHeightPx)
                translationY = -scrollOffset // Parallax effect
                alpha = (-1f / headerHeightPx) * scrollOffset + 1
            }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            repeat(10) {
                Text("header")
            }
        }
    }
}

@Composable
private fun Body(
    scroll: LazyListState
) {

    BoxWithConstraints {
        LazyColumn(
            state = scroll
        ) {
            item {
                Spacer(Modifier.height(headerHeight + 40.dp))
            }
            item {
                Column(modifier = Modifier.fillMaxWidth()) {
                    repeat(100) {
                        Text(
                            text = "Body",
                        )
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    scroll: LazyListState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    modifier: Modifier = Modifier
) {
    val toolbarBottom by remember {
        mutableFloatStateOf(headerHeightPx - toolbarHeightPx)
    }

    val showToolbar by remember {
        derivedStateOf {
            val scrollOffset = scroll.calculateScrollOffset(headerHeightPx)
            scrollOffset >= toolbarBottom
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    listOf(Color.Black, Color.Red)
                )
            ),
            navigationIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            title = {},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )
    }
}

actual class AboutMeScreen actual constructor() : Screen {
    @OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
    @Composable
    override fun Compose(components: Components) {
        val viewModel: AboutMeViewModel = getViewModel<AboutMeViewModel>()
        val navController: NavHostController = rememberAnimatedNavController()

        val density = LocalDensity.current
        val headerHeightPx = with(density) { headerHeight.toPx() }
        val appBarHeightPx = with(density) { toolbarHeight.toPx() }

        val listState = rememberLazyListState()
        val scrollState = rememberScrollState(0)

        val toolbarBottom by remember {
            mutableFloatStateOf(headerHeightPx - appBarHeightPx)
        }

        val showToolbar by remember {
            derivedStateOf {
                val scrollOffset = listState.calculateScrollOffset(headerHeightPx)
                scrollOffset >= toolbarBottom
            }
        }

        val padding by remember {
            derivedStateOf {
                val scrollOffset = listState.calculateScrollOffset(headerHeightPx)
                with(density) { scrollOffset.toDp() + (if (showToolbar) toolbarHeight else 0.dp) }
            }
        }


        val header: AppBarHeader = AppBarHeader(
            lazyListState = listState, headerHeight = headerHeight
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .background(Color.Red)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    repeat(15) {
                        Text(text = "header")
                    }
                }
            }
        }
        Surface(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
                .statusBarsPadding(),
            color = MaterialTheme.colorScheme.background,
            contentColor = contentColorFor(MaterialTheme.colorScheme.background)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Header(
                    scroll = listState,
                    headerHeightPx = headerHeightPx,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(headerHeight)
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
                /* Row(
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
            /* Row(
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
             }*/
            /*LazyColumn(state = listState) {
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
            }*/
        }
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
