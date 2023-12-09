package com.donatas.dprofile.composition.navigation.screens.components.github

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.stopScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.donatas.dprofile.compose.components.DCircularProgressIndicator
import com.donatas.dprofile.compose.components.DPullRefreshIndicator
import com.donatas.dprofile.compose.components.extension.loadingShimmerEffect
import com.donatas.dprofile.compose.components.layout.EmptyView
import com.donatas.dprofile.compose.components.layout.ErrorView
import com.donatas.dprofile.compose.components.text.SectionTitle
import com.donatas.dprofile.compose.theme.getSecondaryColor
import com.donatas.dprofile.composition.R
import com.donatas.dprofile.features.github.GithubListState
import com.donatas.dprofile.features.github.GithubRefreshState
import com.donatas.dprofile.features.github.GithubUser
import com.donatas.dprofile.features.github.GithubViewModel
import com.donatas.dprofile.features.github.UserState
import com.donatas.dprofile.features.github.shared.Repository
import com.donatas.dprofile.features.github.shared.RepositoryListTile
import com.donatas.dprofile.paginator.state.PaginatorState
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@Composable
fun GithubView(model: GithubViewModel) {
    val listState by model.listState.collectAsState()
    val userState by model.user.collectAsState()

    when (val type = listState) {
        is GithubListState.Data -> {
            val refreshState by model.refreshState.collectAsState()
            val paginatorState by model.paginatorState.collectAsState()

            val scrollToTop by model.scrollToTop.collectAsState()
            val endReached by model.endReached.collectAsState()

            Data(repositories = type.data,
                total = type.total,
                refreshState = refreshState,
                paginatorState = paginatorState,
                userState = userState,
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

                    override fun onRetryNextPage() {
                        model.onRetryNextPage()
                    }

                    override fun onSearch() {
                        model.onSearch()
                    }

                    override fun onVisitProfile() {
                        model.onVisitProfile()
                    }
                })
        }

        is GithubListState.Empty -> EmptyView(
            title = type.title, paddingValues = PaddingValues(16.dp), onRefresh = model::onRetryLoading
        )

        is GithubListState.Error -> ErrorView(
            title = type.title,
            message = type.message,
            paddingValues = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
            onRetry = model::onRetryLoading
        )

        is GithubListState.Loading -> Loading()
    }
}

@Composable
private fun Loading() {
    val shimmerBackgroundColor = MaterialTheme.colorScheme.inverseSurface

    val tileGroup = @Composable {
        ShimmerTile(
            titleWidth = 100.dp,
            subtitleWidth = 180.dp,
            background = MaterialTheme.colorScheme.inverseSurface
        )
        ShimmerTile(
            titleWidth = 140.dp,
            subtitleWidth = 100.dp,
            background = MaterialTheme.colorScheme.inverseSurface
        )
        ShimmerTile(
            titleWidth = 180.dp,
            subtitleWidth = 120.dp,
            background = MaterialTheme.colorScheme.inverseSurface
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .loadingShimmerEffect(shimmerBackgroundColor)
            )
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(15.dp)
                    .loadingShimmerEffect(shimmerBackgroundColor)
            )
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(15.dp)
                    .loadingShimmerEffect(shimmerBackgroundColor)
            )
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(15.dp)
                    .loadingShimmerEffect(shimmerBackgroundColor)
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        repeat(5) {
            tileGroup()
        }
    }
}

@Composable
private fun ShimmerTile(
    titleWidth: Dp,
    subtitleWidth: Dp,
    background: Color
) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
                .loadingShimmerEffect(background)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(titleWidth)
                    .height(15.dp)
                    .loadingShimmerEffect(background)
            )
            Box(
                modifier = Modifier
                    .width(subtitleWidth)
                    .height(15.dp)
                    .loadingShimmerEffect(background)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
                .loadingShimmerEffect(background)
        )
    }
}

private interface DataDelegate {
    fun onRepositoryClick(repository: Repository)
    fun onRefresh()
    fun onScrollToTopDone()
    fun onLoadNextPage()
    fun onRetryNextPage()
    fun onSearch()
    fun onVisitProfile()
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun Data(
    repositories: List<Repository>,
    refreshState: GithubRefreshState,
    paginatorState: PaginatorState,
    userState: UserState,
    scrollToTop: Boolean,
    endReached: Boolean,
    total: Int,
    delegate: DataDelegate,
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    val firstVisibleItemIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }

    val pullToRefreshState = rememberPullRefreshState(
        refreshing = refreshState is GithubRefreshState.Refreshing, onRefresh = delegate::onRefresh
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

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.pullRefresh(pullToRefreshState)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), state = listState
            ) {
                item {
                    Profile(
                        state = userState,
                        onVisitProfile = delegate::onVisitProfile
                    )
                }
                stickyHeader {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(start = 16.dp, end = 4.dp, bottom = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SectionTitle(
                            modifier = Modifier.weight(1f),
                            title = "Repositories (${total})",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        IconButton(
                            onClick = delegate::onSearch
                        ) {
                            Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search repositories")
                        }
                    }
                }
                itemsIndexed(repositories) { index, repository ->
                    RepositoryListTile(repository = repository, divided = index < repositories.size - 1, onClick = {
                        delegate.onRepositoryClick(repository)
                    })
                }
                item {
                    if (!endReached && (paginatorState !is PaginatorState.Error || refreshState is GithubRefreshState.Refreshing)) {
                        EndListItem {
                            DCircularProgressIndicator()
                        }
                    }

                    if (!endReached && (paginatorState is PaginatorState.Error) && (refreshState !is GithubRefreshState.Refreshing)) {
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
                refreshing = refreshState is GithubRefreshState.Refreshing,
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
private fun Profile(
    state: UserState,
    onVisitProfile: () -> Unit
) {
    when (state) {
        is UserState.Data -> LoadedProfile(user = state.user, onVisitProfile = onVisitProfile)
        else -> {}
    }
}

@Composable
private fun LoadingProfile() {
    val shimmerBackgroundColor = MaterialTheme.colorScheme.inverseSurface

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .loadingShimmerEffect(shimmerBackgroundColor)
        )
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(15.dp)
                .loadingShimmerEffect(shimmerBackgroundColor)
        )
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(15.dp)
                .loadingShimmerEffect(shimmerBackgroundColor)
        )
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(15.dp)
                .loadingShimmerEffect(shimmerBackgroundColor)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun LoadedProfile(user: GithubUser, onVisitProfile: () -> Unit) {
    val secondaryTextColor = getSecondaryColor()

    val avatarModifier: Modifier = Modifier
        .size(100.dp)
        .border(BorderStroke(1.dp, secondaryTextColor), CircleShape)
        .clip(CircleShape)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        if (user.avatarUrl != null) {
            GlideImage(
                modifier = avatarModifier, model = user.avatarUrl, contentDescription = null
            )
        } else {
            Image(
                modifier = avatarModifier,
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.face),
                contentDescription = null
            )
        }

        Row(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onVisitProfile
            ),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = user.login,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline
            )
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Outlined.Language,
                contentDescription = "Visit profile on Github"
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Group, contentDescription = null, tint = secondaryTextColor
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = user.followers.toString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "followers",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(secondaryTextColor)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "·",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = user.following.toString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "following",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(secondaryTextColor)
            )
        }
        user.location?.let { location ->
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = secondaryTextColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = location,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
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
