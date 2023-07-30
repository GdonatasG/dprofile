package com.donatas.dprofile.features.github.search

import androidx.compose.runtime.Composable
import com.donatas.dprofile.compose.components.layout.AppScaffold
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
        AppScaffold {
            GithubSearchView(viewModel)
        }
    }
}
