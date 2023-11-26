package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.GithubViewModel

actual interface GithubScreenFactory {
    @Composable
    fun Compose(viewModel: GithubViewModel, appTutorial: AppTutorial)
}

actual class GithubScreen actual constructor(
    private val factory: GithubScreenFactory,
    private val viewModel: GithubViewModel,
    private val appTutorial: AppTutorial
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = viewModel, appTutorial = appTutorial)
    }
}
