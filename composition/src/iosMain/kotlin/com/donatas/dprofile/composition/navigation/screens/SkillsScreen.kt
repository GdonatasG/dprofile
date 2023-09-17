package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.skills.SkillsViewModel
import org.koin.core.scope.Scope
import platform.UIKit.UIViewController

actual interface SkillsScreenFactory {
    fun controller(viewModel: SkillsViewModel): UIViewController
}

actual class SkillsScreen actual constructor(
    private val scope: Scope,
    private val factory: SkillsScreenFactory
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = scope.getViewModel())
    }
}
