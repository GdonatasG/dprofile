package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.GithubViewModel
import platform.UIKit.UIViewController

actual interface GithubScreenFactory {
    fun controller(viewModel: GithubViewModel): UIViewController
}

actual class GithubScreen actual constructor(
    private val factory: GithubScreenFactory,
    private val viewModel: GithubViewModel
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = viewModel)
    }
}
