package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeViewModel
import platform.AppKit.NSViewController

actual interface AboutMeScreenFactory {
    fun controller(viewModel: AboutMeViewModel): NSViewController
}

actual class AboutMeScreen actual constructor(
    private val factory: AboutMeScreenFactory,
    private val viewModel: AboutMeViewModel
) : Screen {
    override fun controller(): NSViewController {
        return factory.controller(viewModel = viewModel)
    }
}
