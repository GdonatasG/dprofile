package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.search.GithubSearchViewModel
import org.koin.core.scope.Scope
import platform.UIKit.UIViewController

actual interface GithubSearchScreenFactory {
    fun controller(viewModel: GithubSearchViewModel): UIViewController
}

actual class GithubSearchScreen actual constructor(
    private val scope: Scope,
    private val factory: GithubSearchScreenFactory
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = scope.getViewModel())
    }
}
