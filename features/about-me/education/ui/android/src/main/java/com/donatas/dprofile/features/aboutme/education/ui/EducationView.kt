package com.donatas.dprofile.features.aboutme.education.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.aboutme.education.presentation.EducationViewModel

@Composable
fun EducationView(model: EducationViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(100){
            Text(text = "EducationView$it")
        }
    }
}
