package com.donatas.dprofile.features.github.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.github.presentation.GithubViewModel

@Composable
fun GithubView(model: GithubViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ElevatedButton(
            onClick = model::onSearch
        ) {
            Text(text = "Search")
        }
    }
}
