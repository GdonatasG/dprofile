package com.donatas.dprofile.features.aboutme.education

import androidx.compose.runtime.Composable
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.education.presentation.EducationViewModel
import com.donatas.dprofile.features.aboutme.education.ui.EducationView
import org.koin.androidx.compose.getViewModel

actual class EducationScreen actual constructor() : Screen {
    @Composable
    override fun Compose() {
        val viewModel: EducationViewModel = getViewModel<EducationViewModel>()
        EducationView(viewModel)
    }
}
