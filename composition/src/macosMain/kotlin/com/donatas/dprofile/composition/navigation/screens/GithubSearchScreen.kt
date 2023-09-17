package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.search.GithubSearchViewModel
import org.koin.core.scope.Scope
import platform.AppKit.NSViewController

actual interface GithubSearchScreenFactory {
    fun controller(viewModel: GithubSearchViewModel): NSViewController
}

actual class GithubSearchScreen actual constructor(
    private val scope: Scope,
    private val factory: GithubSearchScreenFactory
) : Screen {
    override fun controller(): NSViewController {
        return factory.controller(viewModel = scope.getViewModel())
    }
}
