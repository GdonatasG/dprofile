package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingViewModel
import org.koin.core.scope.Scope
import platform.AppKit.NSViewController

actual interface RoadToProgrammingScreenFactory {
    fun controller(viewModel: RoadToProgrammingViewModel): NSViewController
}

actual class RoadToProgrammingScreen actual constructor(
    private val scope: Scope,
    private val factory: RoadToProgrammingScreenFactory
) : Screen {
    override fun controller(): NSViewController {
        return factory.controller(viewModel = scope.getViewModel())
    }
}
