package com.donatas.dprofile.composition.presentation.screen

import androidx.compose.runtime.Composable
import com.donatas.dprofile.compose.components.animation.SlideFromSideTransition
import com.donatas.dprofile.composition.presentation.navigation.MainNavGraph
import com.donatas.dprofile.feature.Components
import com.donatas.dprofile.features.github.search.GithubSearchScreen
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.get

@Composable
@Destination(route = GithubSearchScreen.route, style = SlideFromSideTransition::class)
@MainNavGraph
fun GithubSearchScreen(screen: GithubSearchScreen = get<GithubSearchScreen>()) {
    screen.Compose(components = Components())
}
