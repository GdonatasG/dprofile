package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.education.EducationViewModel
import org.koin.core.scope.Scope
import platform.AppKit.NSViewController

actual interface EducationScreenFactory {
    fun controller(viewModel: EducationViewModel): NSViewController
}

actual class EducationScreen actual constructor(
    private val scope: Scope,
    private val factory: EducationScreenFactory
) : Screen {
    override fun controller(): NSViewController {
        return factory.controller(viewModel = scope.getViewModel())
    }
}
