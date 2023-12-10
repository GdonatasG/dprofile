package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeViewModel
import org.koin.core.scope.Scope
import platform.AppKit.NSViewController

actual interface AboutMeScreenFactory {
    fun controller(viewModel: AboutMeViewModel, appTutorial: AppTutorial): NSViewController
}

actual class AboutMeScreen actual constructor(
    private val scope: Scope,
    private val factory: AboutMeScreenFactory,
    private val appTutorial: AppTutorial
) : Screen {
    override fun controller(): NSViewController {
        return factory.controller(viewModel = scope.getViewModel(), appTutorial = appTutorial)
    }
}
