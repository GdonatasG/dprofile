package com.donatas.dprofile.features.aboutme

import com.donatas.dprofile.features.aboutme.presentation.AboutMeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<AboutMeViewModel>() {
        AboutMeViewModel()
    }
}
