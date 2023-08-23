package com.donatas.dprofile.features.github.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.text.SectionTitle
import com.donatas.dprofile.features.github.presentation.GithubViewModel
import com.donatas.dprofile.features.github.shared.RepositoryListTile
import com.donatas.dprofile.loader.state.ListState

@Composable
fun GithubView(model: GithubViewModel) {
    val listState by model.listState.collectAsState()

    when (val type = listState) {
        is ListState.Data -> {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 12.dp)) {
                    SectionTitle(title = "Repositories (${type.total})")
                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    itemsIndexed(type.data) { index, repository ->
                        RepositoryListTile(repository = repository, divided = index < type.data.size - 1, onClick = {
                            model.onDetails(repository)
                        })
                    }
                }
            }
        }

        else -> {}
    }
}
