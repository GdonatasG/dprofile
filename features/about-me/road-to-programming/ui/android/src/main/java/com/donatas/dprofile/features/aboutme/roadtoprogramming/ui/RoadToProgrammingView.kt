package com.donatas.dprofile.features.aboutme.roadtoprogramming.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.aboutme.roadtoprogramming.presentation.RoadToProgrammingViewModel

@Composable
fun RoadToProgrammingView(model: RoadToProgrammingViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "RoadToProgrammingView")
    }
}
