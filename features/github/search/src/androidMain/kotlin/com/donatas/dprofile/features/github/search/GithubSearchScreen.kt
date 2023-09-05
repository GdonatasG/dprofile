package com.donatas.dprofile.features.github.search

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.button.ActionButton
import com.donatas.dprofile.compose.components.button.BackActionButton
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.compose.components.popup.ErrorPopUp
import com.donatas.dprofile.compose.components.state.getImeWithNavigationBarsPadding
import com.donatas.dprofile.compose.components.textfield.DSearchField
import com.donatas.dprofile.feature.Screen
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

        val searchField by viewModel.searchField.collectAsState()
        val popUp by viewModel.popUp.collectAsState()
        val globalSearch by viewModel.globalSearch.collectAsState()

        AppScaffold(appBar = {
            DAppBar(navigationIcon = {
                BackActionButton(
                    onClick = viewModel::onBack
                )
            }, title = {
                DSearchField(modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                    value = searchField,
                    onValueChange = viewModel::onSearch,
                    placeHolder = "Search repositories",
                    contentPadding = PaddingValues(0.dp),
                    onClear = {
                        viewModel.onSearch("")
                    })
            }, centerTitle = false, actions = {
                ActionButton(onClick = viewModel::onFilter) {
                    Icon(imageVector = Icons.Outlined.FilterAlt, contentDescription = "Filter repositories")
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
                Spacer(modifier = Modifier.width(8.dp))
                Switch(modifier = Modifier
                    .scale(0.75f),
                    thumbContent = {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                        )
                    },
                    checked = globalSearch,
                    onCheckedChange = {
                        viewModel.onGlobalSearchChanged(it)
                    }
                )
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
