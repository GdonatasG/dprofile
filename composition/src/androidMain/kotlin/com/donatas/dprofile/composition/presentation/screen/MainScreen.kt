package com.donatas.dprofile.composition.presentation.screen

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.MainScreen
import com.donatas.dprofile.composition.presentation.navigation.MainNavGraph
import com.donatas.dprofile.feature.Components
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.get

@Composable
@Destination
@MainNavGraph(start = true)
fun MainScreen(screen: MainScreen = get<MainScreen>()) {
    screen.Compose(components = Components())
}
