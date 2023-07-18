package com.donatas.dprofile.features.aboutme

import androidx.compose.runtime.Composable
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.presentation.AboutMeViewModel
import com.donatas.dprofile.features.aboutme.ui.AboutMeView
import org.koin.androidx.compose.getViewModel

actual class AboutMeScreen actual constructor() : Screen {
    @Composable
    override fun Compose() {
        val viewModel: AboutMeViewModel = getViewModel<AboutMeViewModel>()

        AppScaffold {
            AboutMeView(viewModel)
        }
    }
}
