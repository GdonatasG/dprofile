package com.donatas.dprofile.composition.di

import com.donatas.dprofile.features.aboutme.AboutMeFeature
import com.donatas.dprofile.features.github.GithubFeature
import org.koin.core.module.Module
import org.koin.dsl.module

internal val featureModule: Module = module {
    single<AboutMeFeature>() {
        AboutMeFeature()
    }

    single<GithubFeature>() {
        GithubFeature()
    }
}
