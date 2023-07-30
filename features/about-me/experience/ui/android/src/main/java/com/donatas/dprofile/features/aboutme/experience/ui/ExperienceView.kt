package com.donatas.dprofile.features.aboutme.experience.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.aboutme.experience.presentation.ExperienceViewModel

@Composable
fun ExperienceView(
    model: ExperienceViewModel
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(100) {
            Text(text = "ExperienceView$it")
        }
    }
    /*  Box(
          modifier = Modifier.fillMaxWidth()
      ) {
          Text(text = "ExperienceView")
      }*/
}
