package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.search.GithubSearchViewModel
import org.koin.core.scope.Scope

actual interface GithubSearchScreenFactory {
    @Composable
    fun Compose(viewModel: GithubSearchViewModel)
}

actual class GithubSearchScreen actual constructor(
    private val scope: Scope,
    private val factory: GithubSearchScreenFactory
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = scope.getViewModel())
    }
}
