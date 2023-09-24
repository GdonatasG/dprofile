package com.donatas.dprofile.composition.navigation.screens.components.github

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.donatas.dprofile.composition.components.Message
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
            if (tutorialState.step == 7 || tutorialState.isFinished) {
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
                Box(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
                ) {
                    Message(
                        message = "In this section, you can review my Github repositories. " +
                                "Don’t forget to try searching and filtering functionalities!"
                    )
                }
                return@AppScaffold
            }

            if (tutorialState.isFinished || tutorialState.step == 7) {
                GithubView(viewModel)

                return@AppScaffold
            }
        }
    }
}
