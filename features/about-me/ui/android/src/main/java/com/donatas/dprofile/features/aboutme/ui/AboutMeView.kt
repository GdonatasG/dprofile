package com.donatas.dprofile.features.aboutme.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.aboutme.presentation.AboutMeViewModel

@Composable
fun AboutMeView(model: AboutMeViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "About Me")
    }
}
