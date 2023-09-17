package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingViewModel
import org.koin.core.scope.Scope

actual interface RoadToProgrammingScreenFactory {
    @Composable
    fun Compose(viewModel: RoadToProgrammingViewModel)
}

actual class RoadToProgrammingScreen actual constructor(
    private val scope: Scope, private val factory: RoadToProgrammingScreenFactory
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = scope.getViewModel())
    }
}
