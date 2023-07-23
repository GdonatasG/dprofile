package com.donatas.dprofile.features.aboutme.skills

import androidx.compose.runtime.Composable
import com.donatas.dprofile.feature.Components
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.skills.presentation.SkillsViewModel
import com.donatas.dprofile.features.aboutme.skills.ui.SkillsView
import org.koin.androidx.compose.getViewModel

actual class SkillsScreen actual constructor() : Screen {
    @Composable
    override fun Compose(components: Components) {
        val viewModel: SkillsViewModel = getViewModel<SkillsViewModel>()
        SkillsView(viewModel)
    }
}
