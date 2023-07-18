package com.donatas.dprofile.features.aboutme

import com.donatas.dprofile.features.aboutme.experience.ExperienceFeature
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun loadModules() = loadKoinModules(
    listOf(
        commonModule, platformModule
    )
)

internal val commonModule = module {
    single<AboutMeScreen> {
        AboutMeScreen()
    }

    single<ExperienceFeature>() {
        ExperienceFeature()
    }
}

internal expect val platformModule: Module
