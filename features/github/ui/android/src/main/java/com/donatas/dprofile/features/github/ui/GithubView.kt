package com.donatas.dprofile.features.github.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.features.github.presentation.GithubViewModel

@Composable
fun GithubView(model: GithubViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            ElevatedButton(
                onClick = model::onSearch
            ) {
                Text(text = "Search")
            }
            ElevatedButton(onClick = model::onDetails) {
                Text(text = "Repo details")
            }
        }
    }
}
