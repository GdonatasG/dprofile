package com.donatas.dprofile.features.aboutme.education.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.aboutme.education.presentation.EducationViewModel

@Composable
fun EducationView(model: EducationViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(100) {
            Text(text = "EducationView")
        }
    }
}
