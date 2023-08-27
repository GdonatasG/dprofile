package com.donatas.dprofile.features.github

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.button.ActionButton
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.compose.components.popup.ErrorPopUp
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
            DAppBar(title = "Github")
        }, snackBar = {
            AnimatedVisibility(
                visible = popUp != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                popUp?.let {
                    ErrorPopUp(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                            .then(if (it.onClick != null) Modifier.clickable { it.onClick?.invoke() } else Modifier),
                        title = it.title
                    )
                }

            }

        }) {
            GithubView(viewModel)
        }
    }
}
