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
import com.donatas.dprofile.compose.components.layout.EmptyView
import com.donatas.dprofile.compose.components.layout.LoadingView
import com.donatas.dprofile.features.github.presentation.GithubViewModel
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.features.github.shared.RepositoryListTile
import com.donatas.dprofile.loader.state.ListState

@Composable
fun GithubView(model: GithubViewModel) {
    val listState by model.listState.collectAsState()

    when (val type = listState) {
        is ListState.Data -> Data(repositories = type.data, total = type.total, delegate = object : DataDelegate {
            override fun onRepositoryClick(repository: Repository) {
                model.onDetails(repository)
            }
        })

        is ListState.Empty -> EmptyView(
            title = type.title, paddingValues = PaddingValues(16.dp), onRefresh = model::onRetry
        )

        is ListState.Loading -> LoadingView(
            paddingValues = PaddingValues(16.dp)
        )

        else -> {}
    }
}

private interface DataDelegate {
    fun onRepositoryClick(repository: Repository)
}

@Composable
private fun Data(
    repositories: List<Repository>, total: Int, delegate: DataDelegate
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)) {
            SectionTitle(title = "Repositories (${total})")
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(repositories) { index, repository ->
                RepositoryListTile(repository = repository, divided = index < repositories.size - 1, onClick = {
                    delegate.onRepositoryClick(repository)
                })
            }
        }
    }
}
