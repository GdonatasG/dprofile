package com.donatas.dprofile.features.github

import androidx.compose.runtime.Composable
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.presentation.GithubViewModel
import com.donatas.dprofile.features.github.ui.GithubView
import org.koin.androidx.compose.getViewModel

actual class GithubScreen actual constructor() : Screen {
    @Composable
    override fun Compose() {
        val viewModel: GithubViewModel = getViewModel<GithubViewModel>()
        AppScaffold {
            GithubView(viewModel)
        }
    }
}
