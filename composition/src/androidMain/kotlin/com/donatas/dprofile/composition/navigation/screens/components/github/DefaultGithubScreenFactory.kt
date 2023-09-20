package com.donatas.dprofile.composition.navigation.screens.components.github

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.compose.components.popup.ErrorPopUp
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.navigation.screens.GithubScreenFactory
import com.donatas.dprofile.features.github.GithubViewModel

class DefaultGithubScreenFactory : GithubScreenFactory {

    @Composable
    override fun Compose(viewModel: GithubViewModel, appTutorial: AppTutorial) {
        val popUp by viewModel.popUp.collectAsState()
        val tutorialState by appTutorial.state.collectAsState()


        AppScaffold(appBar = {
            DAppBar(title = "Github")
        }, snackBar = {
            if (tutorialState.step == 7) {
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
            }
        }) {
            if (tutorialState.step == 6) {
                Text(text = "Some tutorial message")
                return@AppScaffold
            }

            if (tutorialState.isFinished || tutorialState.step == 7) {
                GithubView(viewModel)

                return@AppScaffold
            }
        }
    }
}
