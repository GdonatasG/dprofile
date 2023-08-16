package com.donatas.dprofile.features.github.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.text.SectionTitle
import com.donatas.dprofile.features.github.presentation.GithubViewModel

@Composable
fun GithubView(model: GithubViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 12.dp)) {
            SectionTitle(title = "Repositories (10)")
        }
    }
}
