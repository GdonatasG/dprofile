package com.donatas.dprofile.features.aboutme.roadtoprogramming

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
    single<RoadToProgrammingScreen> {
        RoadToProgrammingScreen()
    }
}

internal expect val platformModule: Module
