package com.donatas.dprofile.features.github.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.github.search.presentation.GithubSearchViewModel

@Composable
fun GithubSearchView(model: GithubSearchViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            ElevatedButton(onClick = model::onBack) {
                Text(text = "Back")
            }
            ElevatedButton(onClick = model::onFilter) {
                Text(text = "Filter")
            }
            ElevatedButton(onClick = model::onDetails) {
                Text(text = "Repo details")
            }
        }
    }
}
