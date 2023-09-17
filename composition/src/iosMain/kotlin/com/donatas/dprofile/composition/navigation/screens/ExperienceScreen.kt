package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.experience.ExperienceViewModel
import org.koin.core.scope.Scope
import platform.UIKit.UIViewController

actual interface ExperienceScreenFactory {
    fun controller(viewModel: ExperienceViewModel): UIViewController
}

actual class ExperienceScreen actual constructor(
    private val scope: Scope,
    private val factory: ExperienceScreenFactory
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = scope.getViewModel())
    }
}
