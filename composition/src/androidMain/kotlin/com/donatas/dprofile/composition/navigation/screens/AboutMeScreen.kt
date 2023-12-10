package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.di.extensions.getNavViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeViewModel
import org.koin.core.scope.Scope

actual interface AboutMeScreenFactory {
    @Composable
    fun Compose(viewModel: AboutMeViewModel, appTutorial: AppTutorial)
}

actual class AboutMeScreen actual constructor(
    private val scope: Scope,
    private val factory: AboutMeScreenFactory,
    private val appTutorial: AppTutorial
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = getNavViewModel(scope), appTutorial = appTutorial)
    }
}
