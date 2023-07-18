package com.donatas.dprofile.features.template.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.template.presentation.TestViewModel

@Composable
fun TestView(model: TestViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "TestView")
    }
}
