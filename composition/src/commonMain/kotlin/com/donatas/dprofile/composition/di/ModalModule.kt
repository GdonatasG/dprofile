package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.navigation.core.ModalFactory
import com.donatas.dprofile.composition.navigation.screens.FiltersModalFactory
import org.koin.dsl.module

internal fun modalModule(factory: ModalFactory) = module {
    single<ModalFactory> {
        factory
    }

    single<FiltersModalFactory> {
        get<ModalFactory>().filters()
    }
}
