package com.donatas.dprofile.features.aboutme

import com.donatas.dprofile.features.aboutme.education.EducationFeature
import com.donatas.dprofile.features.aboutme.experience.ExperienceFeature
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingFeature
import com.donatas.dprofile.features.aboutme.skills.SkillsFeature
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

    single<EducationFeature>() {
        EducationFeature()
    }

    single<SkillsFeature>() {
        SkillsFeature()
    }

    single<RoadToProgrammingFeature>() {
        RoadToProgrammingFeature()
    }
}

internal expect val platformModule: Module
