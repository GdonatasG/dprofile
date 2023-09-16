package com.donatas.dprofile.features.github.search

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.button.ActionButton
import com.donatas.dprofile.compose.components.button.BackActionButton
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.compose.components.popup.ErrorPopUp
import com.donatas.dprofile.compose.components.state.getImeWithNavigationBarsPadding
import com.donatas.dprofile.compose.components.textfield.DSearchField
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.search.presentation.AppliedFiltersState
import com.donatas.dprofile.features.github.search.presentation.GithubSearchViewModel
import com.donatas.dprofile.features.github.search.ui.GithubSearchView
import org.koin.androidx.compose.getViewModel

actual class GithubSearchScreen actual constructor() : Screen {

    companion object {
        const val route: String = "github_search_screen"
    }

    @Composable
    override fun Compose() {
        val viewModel: GithubSearchViewModel = getViewModel<GithubSearchViewModel>()

        BackHandler {
            viewModel.onBack()
        }

        val searchField by viewModel.searchField.collectAsState()
        val popUp by viewModel.popUp.collectAsState()
        val globalSearch by viewModel.globalSearch.collectAsState()
        val appliedFiltersState by viewModel.appliedFiltersState.collectAsState()

        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        val interactionSource = remember { MutableInteractionSource() }

        val lifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_START -> {
                        viewModel.onAppear()
                    }

                    Lifecycle.Event.ON_STOP -> {
                        viewModel.onDisappear()
                    }

                    else -> {}
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }


        Box(modifier = Modifier.clickable(interactionSource = interactionSource, indication = null) {
            focusManager.clearFocus(true)
        }) {
            AppScaffold(appBar = {
                DAppBar(navigationIcon = {
                    BackActionButton(
                        onClick = viewModel::onBack
                    )
                }, title = {
                    DSearchField(modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                        focusRequester = focusRequester,
                        value = searchField,
                        onValueChange = viewModel::onSearch,
                        placeHolder = "Search repositories",
                        contentPadding = PaddingValues(0.dp),
                        onClear = {
                            viewModel.onSearch("")
                        })
                }, centerTitle = false, actions = {
                    ActionButton(onClick = {
                        focusManager.clearFocus(true)
                        viewModel.onFilter()
                    }) {
                        Box {
                            Icon(
                                painter = painterResource(id = R.drawable.filter),
                                contentDescription = "Filter repositories"
                            )
                            if (appliedFiltersState is AppliedFiltersState.Content) {
                                Icon(
                                    painter = painterResource(id = R.drawable.filter_badge),
                                    contentDescription = "Filter repositories",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }

                })
            }, tabBar = {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.clickable(interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                focusManager.clearFocus(true)
                                viewModel.onDescribeGlobalSearch()
                            }), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = "Describe global search")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            modifier = Modifier.weight(1f, fill = false),
                            text = "Global search",
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Switch(modifier = Modifier.scale(0.75f), thumbContent = {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                        )
                    }, checked = globalSearch, onCheckedChange = {
                        viewModel.onGlobalSearchChanged(it)
                    })
                }
            }, snackBar = {
                AnimatedVisibility(
                    modifier = Modifier.padding(bottom = getImeWithNavigationBarsPadding(initialPadding = 0.dp).value),
                    visible = popUp != null,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    popUp?.let {
                        ErrorPopUp(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                            title = it.title,
                            onClick = it.onClick
                        )
                    }

                }

            }) {
                GithubSearchView(model = viewModel)
            }
        }
    }
}
