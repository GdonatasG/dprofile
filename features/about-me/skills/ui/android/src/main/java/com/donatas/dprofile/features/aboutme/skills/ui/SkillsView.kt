package com.donatas.dprofile.features.aboutme.skills.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.aboutme.skills.presentation.SkillsViewModel

@Composable
fun SkillsView(model: SkillsViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "SkillsView")
    }
}
