package com.donatas.dprofile.composition.di

import com.donatas.dprofile.features.aboutme.AboutMeFeature
import com.donatas.dprofile.features.contacts.ContactsFeature
import com.donatas.dprofile.features.filter.FilterFeature
import com.donatas.dprofile.features.github.GithubFeature
import com.donatas.dprofile.features.github.search.GithubSearchFeature
import org.koin.core.module.Module
import org.koin.dsl.module

internal val featureModule: Module = module {
    single<AboutMeFeature>() {
        AboutMeFeature()
    }

    single<GithubFeature>() {
        GithubFeature()
    }

    single<GithubSearchFeature>() {
        GithubSearchFeature()
    }

    single<FilterFeature>() {
        FilterFeature()
    }

    single<ContactsFeature>() {
        ContactsFeature()
    }
}
