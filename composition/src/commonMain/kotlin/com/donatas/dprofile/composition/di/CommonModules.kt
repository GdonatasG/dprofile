package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.navigation.core.ModalFactory
import com.donatas.dprofile.composition.navigation.core.ScreenFactory
import com.donatas.dprofile.composition.navigation.flow.GithubSearchFlow
import com.donatas.dprofile.composition.navigation.flow.MainFlow
import com.donatas.dprofile.preferences.Preferences
import org.koin.dsl.module

internal fun commonModules(screenFactory: ScreenFactory, modalFactory: ModalFactory) = listOf(
    screenModule(factory = screenFactory),
    modalModule(factory = modalFactory),

    flowModule,
    githubServicesModule,
    networkingModule,
    internalLibsModule,

    MainFlow.module,
    GithubSearchFlow.module,
    module {
        single<AppTutorial> {
            AppTutorial(preferences = get<Preferences>())
        }
    }
)
