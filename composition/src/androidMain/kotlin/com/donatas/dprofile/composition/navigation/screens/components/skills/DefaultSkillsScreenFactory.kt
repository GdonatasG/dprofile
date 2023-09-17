package com.donatas.dprofile.composition.navigation.screens.components.skills

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.navigation.screens.SkillsScreenFactory
import com.donatas.dprofile.features.aboutme.skills.SkillsViewModel

class DefaultSkillsScreenFactory : SkillsScreenFactory {
    @Composable
    override fun Compose(viewModel: SkillsViewModel) {
        SkillsView(viewModel)
    }
}
