package com.donatas.dprofile.features.aboutme.roadtoprogramming

import androidx.compose.runtime.Composable
import com.donatas.dprofile.feature.Components
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.roadtoprogramming.presentation.RoadToProgrammingViewModel
import com.donatas.dprofile.features.aboutme.roadtoprogramming.ui.RoadToProgrammingView
import org.koin.androidx.compose.getViewModel

actual class RoadToProgrammingScreen actual constructor() : Screen {
    @Composable
    override fun Compose(components: Components) {
        val viewModel: RoadToProgrammingViewModel = getViewModel<RoadToProgrammingViewModel>()
        RoadToProgrammingView(viewModel)
    }
}
