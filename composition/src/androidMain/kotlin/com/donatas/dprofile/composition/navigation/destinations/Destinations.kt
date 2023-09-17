package com.donatas.dprofile.composition.navigation.destinations

import androidx.compose.runtime.Composable
import com.donatas.dprofile.compose.components.animation.SlideFromSideTransition
import com.donatas.dprofile.composition.di.Scopes
import com.donatas.dprofile.composition.extensions.getScope
import com.donatas.dprofile.composition.navigation.screens.BottomTabBarScreen
import com.donatas.dprofile.composition.navigation.screens.GithubSearchScreen
import com.donatas.dprofile.composition.presentation.navigation.MainNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
@MainNavGraph(start = true)
fun Splash() {
}

@Composable
@Destination(style = SlideFromSideTransition::class)
@MainNavGraph
fun BottomTabBar() {
    val scope = getScope(scope = Scopes.BOTTOM_TAB)

    val screen = scope.get<BottomTabBarScreen>()

    screen.Compose()
}

@Composable
@Destination(style = SlideFromSideTransition::class)
@MainNavGraph
fun GithubSearch() {
    val scope = getScope(scope = Scopes.GITHUB_SEARCH)

    val screen = scope.get<GithubSearchScreen>()

    screen.Compose()
}
