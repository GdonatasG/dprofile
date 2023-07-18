package com.donatas.dprofile.features.aboutme.education.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.aboutme.education.presentation.EducationViewModel

@Composable
fun EducationView(model: EducationViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "EducationView")
    }
}
