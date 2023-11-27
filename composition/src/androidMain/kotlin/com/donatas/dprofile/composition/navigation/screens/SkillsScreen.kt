package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.di.extensions.getNavViewModel
import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.skills.SkillsViewModel
import org.koin.core.scope.Scope

actual interface SkillsScreenFactory {
    @Composable
    fun Compose(viewModel: SkillsViewModel)
}

actual class SkillsScreen actual constructor(
    private val scope: Scope,
    private val factory: SkillsScreenFactory
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = getNavViewModel(scope = scope))
    }
}
