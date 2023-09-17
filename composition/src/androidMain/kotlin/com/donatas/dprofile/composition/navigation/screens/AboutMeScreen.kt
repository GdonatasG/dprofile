package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeViewModel

actual interface AboutMeScreenFactory {
    @Composable
    fun Compose(viewModel: AboutMeViewModel)
}

actual class AboutMeScreen actual constructor(
    private val factory: AboutMeScreenFactory,
    private val viewModel: AboutMeViewModel
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = viewModel)
    }
}
