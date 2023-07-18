package com.donatas.dprofile.features.aboutme.roadtoprogramming

import com.donatas.dprofile.features.aboutme.roadtoprogramming.presentation.RoadToProgrammingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<RoadToProgrammingViewModel>() {
        RoadToProgrammingViewModel()
    }
}
