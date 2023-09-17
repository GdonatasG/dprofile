package com.donatas.dprofile.composition.navigation.screens.components.github

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.compose.components.popup.ErrorPopUp
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory
import com.donatas.dprofile.features.github.GithubViewModel

class DefaultGithubScreenFactory: GithubScreenFactory {

    @Composable
    override fun Compose(viewModel: GithubViewModel) {
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
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        title = it.title,
                        onClick = it.onClick
                    )
                }

            }

        }) {
            GithubView(viewModel)
        }
    }
}
