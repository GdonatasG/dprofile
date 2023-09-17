package com.donatas.dprofile.composition.navigation.screens.components.road_to_programming

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.navigation.screens.RoadToProgrammingScreenFactory
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingViewModel

class DefaultRoadToProgrammingScreenFactory: RoadToProgrammingScreenFactory {
    @Composable
    override fun Compose(viewModel: RoadToProgrammingViewModel) {
        RoadToProgrammingView(viewModel)
    }
}
