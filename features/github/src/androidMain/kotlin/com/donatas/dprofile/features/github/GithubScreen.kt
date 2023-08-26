package com.donatas.dprofile.features.github

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.button.ActionButton
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.presentation.GithubViewModel
import com.donatas.dprofile.features.github.ui.GithubView
import org.koin.androidx.compose.getViewModel

actual class GithubScreen actual constructor() : Screen {
    @Composable
    override fun Compose() {
        val viewModel: GithubViewModel = getViewModel<GithubViewModel>()
        val popUp by viewModel.popUp.collectAsState()

        AppScaffold(appBar = {
            DAppBar(title = "Github", actions = {
                ActionButton(onClick = { }) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search repositories")
                }
            })
        }, snackBar = {
            AnimatedVisibility(visible = popUp != null) {
                popUp?.let {
                    Snackbar(modifier = Modifier
                        .then(if (it.onClick != null) Modifier.clickable {
                            it.onClick?.invoke()
                        } else Modifier)
                        .padding(
                            start = 16.dp, end = 16.dp, bottom = 16.dp
                        ), contentColor = MaterialTheme.colorScheme.inverseOnSurface) {
                        Row(
                            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.error
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = it.title,
                                color = MaterialTheme.colorScheme.inverseOnSurface
                            )
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    }
                }

            }

        }) {
            GithubView(viewModel)
        }
    }
}
