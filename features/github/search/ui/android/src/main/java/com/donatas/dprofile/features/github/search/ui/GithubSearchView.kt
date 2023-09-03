package com.donatas.dprofile.features.github.search.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.stopScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.DCircularProgressIndicator
import com.donatas.dprofile.compose.components.DPullRefreshIndicator
import com.donatas.dprofile.compose.components.layout.EmptyView
import com.donatas.dprofile.compose.components.layout.ErrorView
import com.donatas.dprofile.compose.components.layout.LoadingView
import com.donatas.dprofile.compose.components.state.getImePadding
import com.donatas.dprofile.compose.components.state.getImeWithNavigationBarsPadding
import com.donatas.dprofile.compose.components.text.SectionTitle
import com.donatas.dprofile.features.github.search.presentation.GithubSearchViewModel
import com.donatas.dprofile.features.github.search.presentation.GithubSearchViewState
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.features.github.shared.RepositoryListTile
import com.donatas.dprofile.loader.state.ListState
import com.donatas.dprofile.loader.state.RefreshState
import com.donatas.dprofile.paginator.state.PaginatorState
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@Composable
fun GithubSearchView(model: GithubSearchViewModel) {
    val viewState by model.viewState.collectAsState()

    when (val viewType = viewState) {
        is GithubSearchViewState.Idle -> IdleView(message = viewType.message)
        is GithubSearchViewState.Searched -> {
            val listState by model.listState.collectAsState()
            val refreshState by model.refreshState.collectAsState()
            val paginatorState by model.paginatorState.collectAsState()

            val scrollToTop by model.scrollToTop.collectAsState()
            val endReached by model.endReached.collectAsState()


            when (val type = listState) {
                is ListState.Data -> Box(
                    modifier = Modifier.padding(
                        bottom = getImeWithNavigationBarsPadding(
                            initialPadding = 0.dp
                        ).value
                    )
                ) {
                    Data(repositories = type.data,
                        refreshState = refreshState,
                        paginatorState = paginatorState,
                        scrollToTop = scrollToTop,
                        endReached = endReached,
                        total = type.total,
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

                            override fun onRetryNextPage() {
                                model.onRetryNextPage()
                            }
                        })
                }

                is ListState.Empty -> EmptyView(
                    title = type.title,
                    paddingValues = defaultPaddingValues(),
                    onRefresh = model::onRetryLoading
                )

                is ListState.Error -> ErrorView(
                    title = type.title,
                    message = type.message,
                    paddingValues = defaultPaddingValues(),
                    onRetry = model::onRetryLoading
                )

                is ListState.Loading -> LoadingView(
                    paddingValues = defaultPaddingValues()
                )
            }
        }
    }
}

@Composable
private fun defaultPaddingValues(): PaddingValues = PaddingValues(
    top = 16.dp, start = 16.dp, end = 16.dp, bottom = getImeWithNavigationBarsPadding().value
)

private interface DataDelegate {
    fun onRepositoryClick(repository: Repository)
    fun onRefresh()
    fun onScrollToTopDone()
    fun onLoadNextPage()
    fun onRetryNextPage()
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
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
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val firstVisibleItemIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }

    val pullToRefreshState = rememberPullRefreshState(
        refreshing = refreshState is RefreshState.Refreshing, onRefresh = delegate::onRefresh
    )

    LaunchedEffect(scrollToTop) {
        if (scrollToTop) {
            listState.stopScroll(scrollPriority = MutatePriority.PreventUserInput)
            listState.scrollToItem(0)
        }

        delegate.onScrollToTopDone()
    }

    val shouldLoadMore = remember {
        derivedStateOf {
            val visibleItemsInfo = listState.layoutInfo.visibleItemsInfo
            val loadMore = if (listState.layoutInfo.totalItemsCount == 0) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()

                lastVisibleItem.index + 1 >= listState.layoutInfo.totalItemsCount - 12
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.pullRefresh(pullToRefreshState)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), state = listState
            ) {
                stickyHeader {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    ) {
                        SectionTitle(
                            title = "Repositories (${total})", maxLines = 1, overflow = TextOverflow.Ellipsis
                        )
                    }
                }
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
                            ElevatedButton(onClick = delegate::onRetryNextPage) {
                                Icon(
                                    imageVector = Icons.Default.Refresh, contentDescription = "Load more repositories"
                                )
                            }
                            Spacer(modifier = Modifier.width(24.dp))
                            Text(
                                text = "Unable to load more repositories",
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(60.dp))
                }
            }

            DPullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = refreshState is RefreshState.Refreshing,
                state = pullToRefreshState
            )

            if (firstVisibleItemIndex.value > 0) {
                SmallFloatingActionButton(
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                        .align(Alignment.BottomEnd)
                        .size(48.dp),
                    onClick = {
                        scope.launch {
                            listState.scrollToItem(0)
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                    contentColor = Color.White,
                    elevation = FloatingActionButtonDefaults.elevation(
                        0.dp, 0.dp, 0.dp, 0.dp
                    ) // disable elevation because of background with opacity
                ) {
                    Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = "Scroll to top")
                }
            }
        }

    }
}

@Composable
private fun EndListItem(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}

@Composable
private fun IdleView(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center
        )
    }
}
