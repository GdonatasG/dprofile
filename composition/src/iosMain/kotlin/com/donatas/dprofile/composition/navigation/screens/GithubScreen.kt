package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.GithubViewModel
import org.koin.core.scope.Scope
import platform.UIKit.UIViewController

actual interface GithubScreenFactory {
    fun controller(viewModel: GithubViewModel, appTutorial: AppTutorial): UIViewController
}

actual class GithubScreen actual constructor(
    private val scope: Scope,
    private val factory: GithubScreenFactory,
    private val appTutorial: AppTutorial
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = scope.getViewModel(), appTutorial = appTutorial)
    }
}
