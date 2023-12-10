package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeViewModel
import org.koin.core.scope.Scope
import platform.UIKit.UIViewController

actual interface AboutMeScreenFactory {
    fun controller(viewModel: AboutMeViewModel, appTutorial: AppTutorial): UIViewController
}

actual class AboutMeScreen actual constructor(
    private val scope: Scope,
    private val factory: AboutMeScreenFactory,
    private val appTutorial: AppTutorial
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = scope.getViewModel(), appTutorial = appTutorial)
    }
}
