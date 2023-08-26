package com.donatas.dprofile.features.github.ui

import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.stopScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.DCircularProgressIndicator
import com.donatas.dprofile.compose.components.DPullRefreshIndicator
import com.donatas.dprofile.compose.components.layout.EmptyView
import com.donatas.dprofile.compose.components.layout.ErrorView
import com.donatas.dprofile.compose.components.layout.LoadingView
import com.donatas.dprofile.compose.components.text.SectionTitle
import com.donatas.dprofile.features.github.presentation.GithubViewModel
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.features.github.shared.RepositoryListTile
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import com.donatas.dprofile.paginator.state.PaginatorState
import kotlinx.coroutines.flow.flow

@Composable
fun GithubView(model: GithubViewModel) {
    val listState by model.listState.collectAsState()

    when (val type = listState) {
        is ListState.Data -> {
            val refreshState by model.refreshState.collectAsState()
            val paginatorState by model.paginatorState.collectAsState()

            val scrollToTop by model.scrollToTop.collectAsState()
            val endReached by model.endReached.collectAsState()

            Data(
                repositories = type.data,
                total = type.total,
                refreshState = refreshState,
                paginatorState = paginatorState,
                scrollToTop = scrollToTop,
                endReached = endReached,
                delegate = object : DataDelegate {
                    override fun onRepositoryClick(repository: Repository) {
                        model.onDetails(repository)
                    }

                    override fun onRefresh() {
                        model.onRefresh()
                    }

                    override fun onScrollToTopDone() {
                        model.onScrollToTopDone()
                    }

                    override fun onLoadNextPage() {
                        model.onLoadNextPage()
                    }

                    override fun onRetry() {
                        model.onRetry()
                    }
                }
            )
        }

        is ListState.Empty -> EmptyView(
            title = type.title, paddingValues = PaddingValues(16.dp), onRefresh = model::onRetry
        )

        is ListState.Error -> ErrorView(
            title = type.title,
            message = type.message,
            paddingValues = PaddingValues(16.dp),
            onRetry = model::onRetry
        )

        is ListState.Loading -> LoadingView(
            paddingValues = PaddingValues(16.dp)
        )
    }
}

private interface DataDelegate {
    fun onRepositoryClick(repository: Repository)
    fun onRefresh()
    fun onScrollToTopDone()
    fun onLoadNextPage()
    fun onRetry()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Data(
    repositories: List<Repository>,
    refreshState: RefreshState,
    paginatorState: PaginatorState,
    scrollToTop: Boolean,
    endReached: Boolean,
    total: Int,
    delegate: DataDelegate
) {
    val listState = rememberLazyListState()

    val pullToRefreshState =
        rememberPullRefreshState(refreshing = refreshState is RefreshState.Refreshing, onRefresh = delegate::onRefresh)

    LaunchedEffect(scrollToTop) {
        if (scrollToTop) {
            listState.stopScroll(scrollPriority = MutatePriority.PreventUserInput)
            listState.scrollToItem(0)
            delegate.onScrollToTopDone()
        }
    }

    val shouldLoadMore = remember {
        derivedStateOf {
            val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
            val loadMore = if (listState.layoutInfo.totalItemsCount == 0) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()

                lastVisibleItem.index + 1 >= listState.layoutInfo.totalItemsCount - 11
            }

            if (loadMore) {
                delegate.onLoadNextPage()
            }
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        flow<Boolean> {
            shouldLoadMore.value
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 12.dp)) {
            SectionTitle(title = "Repositories (${total})")
        }
        Box(
            modifier = Modifier
                .pullRefresh(pullToRefreshState)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = listState
            ) {
                itemsIndexed(repositories) { index, repository ->
                    RepositoryListTile(repository = repository, divided = index < repositories.size - 1, onClick = {
                        delegate.onRepositoryClick(repository)
                    })
                }
                item {
                    if (!endReached && (paginatorState !is PaginatorState.Error || refreshState is RefreshState.Refreshing)) {
                        EndListItem {
                            DCircularProgressIndicator()
                        }
                    }
                    if (!endReached && (paginatorState is PaginatorState.Error) && (refreshState !is RefreshState.Refreshing)) {
                        EndListItem {
                            ElevatedButton(onClick = delegate::onRetry) {
                                Text(text = "Load more")
                            }
                        }
                    }
                }
            }

            DPullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = refreshState is RefreshState.Refreshing,
                state = pullToRefreshState
            )
        }

    }
}

@Composable
private fun EndListItem(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}
