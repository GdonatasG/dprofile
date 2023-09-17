package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeViewModel
import platform.UIKit.UIViewController

actual interface AboutMeScreenFactory {
    fun controller(viewModel: AboutMeViewModel): UIViewController
}

actual class AboutMeScreen actual constructor(
    private val factory: AboutMeScreenFactory,
    private val viewModel: AboutMeViewModel
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = viewModel)
    }
}
