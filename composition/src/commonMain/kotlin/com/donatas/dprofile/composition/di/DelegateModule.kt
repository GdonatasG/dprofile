package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.features.filter.presentation.FiltersDelegate
import org.koin.core.module.Module
import org.koin.dsl.module

internal val delegateModule: Module = module {
    single<FiltersDelegate> {
        object : FiltersDelegate {
            override fun onBack() {
                get<Navigator>().closeModal()
            }

        }
    }
}
