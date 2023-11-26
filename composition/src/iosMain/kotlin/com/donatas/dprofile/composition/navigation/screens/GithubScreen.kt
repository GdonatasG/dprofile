package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.GithubViewModel
import platform.UIKit.UIViewController

actual interface GithubScreenFactory {
    fun controller(viewModel: GithubViewModel, appTutorial: AppTutorial): UIViewController
}

actual class GithubScreen actual constructor(
    private val factory: GithubScreenFactory,
    private val viewModel: GithubViewModel,
    private val appTutorial: AppTutorial
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = viewModel, appTutorial = appTutorial)
    }
}
