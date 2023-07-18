package com.donatas.dprofile.features.filter

import com.donatas.dprofile.features.filter.presentation.FilterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<FilterViewModel>() {
        FilterViewModel()
    }
}
