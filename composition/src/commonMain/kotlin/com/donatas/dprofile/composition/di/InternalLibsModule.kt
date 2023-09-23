package com.donatas.dprofile.composition.di

import com.donatas.dprofile.preferences.Preferences
import org.koin.dsl.module

internal val internalLibsModule = module {
    single<Preferences> {
        Preferences.shared
    }
}
