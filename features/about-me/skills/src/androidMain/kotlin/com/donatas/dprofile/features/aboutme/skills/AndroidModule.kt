package com.donatas.dprofile.features.aboutme.skills

import com.donatas.dprofile.features.aboutme.skills.presentation.SkillsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<SkillsViewModel>() {
        SkillsViewModel()
    }
}
