package com.donatas.dprofile.features.aboutme.education

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun loadModules() = loadKoinModules(
    listOf(
        commonModule,
        platformModule
    )
)

internal val commonModule = module {
    single<EducationScreen> {
        EducationScreen()
    }
}

internal expect val platformModule: Module
