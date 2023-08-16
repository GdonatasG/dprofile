package com.donatas.dprofile.features.github.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.text.SectionTitle
import com.donatas.dprofile.features.github.presentation.GithubViewModel
import com.donatas.dprofile.features.github.shared.RepositoryListTile

@Composable
fun GithubView(model: GithubViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 12.dp)) {
            SectionTitle(title = "Repositories (10)")
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(model.repositories) { index, repository ->
                RepositoryListTile(repository = repository, divided = index < model.repositories.size - 1, onClick = {
                    model.onDetails(repository)
                })
            }
        }
    }
}
