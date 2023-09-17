package com.donatas.dprofile.composition.navigation.screens.components.education

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.navigation.screens.EducationScreenFactory
import com.donatas.dprofile.features.aboutme.education.EducationViewModel

class DefaultEducationScreenFactory: EducationScreenFactory {
    @Composable
    override fun Compose(viewModel: EducationViewModel) {
        EducationView(viewModel)
    }
}
