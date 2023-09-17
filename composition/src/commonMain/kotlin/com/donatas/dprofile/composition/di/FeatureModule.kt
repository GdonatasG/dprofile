package com.donatas.dprofile.composition.di

import com.donatas.dprofile.features.aboutme.education.EducationFeature
import com.donatas.dprofile.features.aboutme.experience.ExperienceFeature
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingFeature
import com.donatas.dprofile.features.aboutme.skills.SkillsFeature
import com.donatas.dprofile.features.filter.FilterFeature
import com.donatas.dprofile.features.github.search.GithubSearchFeature
import org.koin.core.module.Module
import org.koin.dsl.module

internal val featureModule: Module = module {
    single<GithubSearchFeature>() {
        GithubSearchFeature()
    }

    single<FilterFeature>() {
        FilterFeature()
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
