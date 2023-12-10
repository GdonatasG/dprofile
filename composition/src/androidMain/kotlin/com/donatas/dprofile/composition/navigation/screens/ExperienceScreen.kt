package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.di.extensions.getNavViewModel
import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.experience.ExperienceViewModel
import org.koin.core.scope.Scope

actual interface ExperienceScreenFactory {
    @Composable
    fun Compose(viewModel: ExperienceViewModel)
}

actual class ExperienceScreen actual constructor(
    private val scope: Scope,
    private val factory: ExperienceScreenFactory
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = getNavViewModel(scope = scope))
    }
}
