package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.extensions.getViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.education.EducationViewModel
import org.koin.core.scope.Scope

actual interface EducationScreenFactory {
    @Composable
    fun Compose(viewModel: EducationViewModel)
}

actual class EducationScreen actual constructor(
    private val scope: Scope,
    private val factory: EducationScreenFactory
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = scope.getViewModel())
    }
}
