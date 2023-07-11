package com.donatas.dprofile.features.template

import com.donatas.dprofile.features.template.presentation.TestViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    factory<TestViewModel> {
        TestViewModel()
    }
}
