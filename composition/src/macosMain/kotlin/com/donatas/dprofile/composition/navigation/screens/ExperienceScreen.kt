package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.experience.ExperienceViewModel
import org.koin.core.scope.Scope
import platform.AppKit.NSViewController

actual interface ExperienceScreenFactory {
    fun controller(viewModel: ExperienceViewModel): NSViewController
}

actual class ExperienceScreen actual constructor(
    private val scope: Scope,
    private val factory: ExperienceScreenFactory
) : Screen {
    override fun controller(): NSViewController {
        return factory.controller(viewModel = scope.getViewModel())
    }
}
