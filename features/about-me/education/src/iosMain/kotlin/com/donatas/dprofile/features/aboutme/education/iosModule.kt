package com.donatas.dprofile.features.aboutme.education

import com.donatas.dprofile.features.aboutme.education.presentation.EducationViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    factory<EducationViewModel> {
        EducationViewModel()
    }
}
