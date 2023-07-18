package com.donatas.dprofile.features.aboutme.experience.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.aboutme.experience.presentation.ExperienceViewModel

@Composable
fun ExperienceView(model: ExperienceViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "ExperienceView")
    }
}
