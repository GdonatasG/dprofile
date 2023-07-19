package com.donatas.dprofile.features.aboutme.experience

import com.donatas.dprofile.features.aboutme.experience.presentation.ExperienceViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    factory<ExperienceViewModel> {
        ExperienceViewModel()
    }
}