package com.donatas.dprofile.features.aboutme.experience

import androidx.compose.runtime.Composable
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.experience.presentation.ExperienceViewModel
import com.donatas.dprofile.features.aboutme.experience.ui.ExperienceView
import org.koin.androidx.compose.getViewModel

actual class ExperienceScreen actual constructor() : Screen {
    @Composable
    override fun Compose() {
        val viewModel: ExperienceViewModel = getViewModel<ExperienceViewModel>()
        ExperienceView(
            model = viewModel
        )
    }
}
