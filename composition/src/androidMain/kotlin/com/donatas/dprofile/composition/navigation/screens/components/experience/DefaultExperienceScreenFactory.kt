package com.donatas.dprofile.composition.navigation.screens.components.experience

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.navigation.screens.ExperienceScreenFactory
import com.donatas.dprofile.features.aboutme.experience.ExperienceViewModel

class DefaultExperienceScreenFactory : ExperienceScreenFactory {
    @Composable
    override fun Compose(viewModel: ExperienceViewModel) {
        ExperienceView(
            model = viewModel
        )
    }
}
