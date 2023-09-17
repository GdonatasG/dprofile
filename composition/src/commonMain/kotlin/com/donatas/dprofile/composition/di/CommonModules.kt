package com.donatas.dprofile.composition.di

import com.donatas.dprofile.composition.navigation.core.ModalFactory
import com.donatas.dprofile.composition.navigation.core.ScreenFactory
import com.donatas.dprofile.composition.navigation.flow.GithubSearchFlow
import com.donatas.dprofile.composition.navigation.flow.MainFlow

internal fun commonModules(screenFactory: ScreenFactory, modalFactory: ModalFactory) = listOf(
    screenModule(factory = screenFactory),
    modalModule(factory = modalFactory),

    flowModule,
    githubServicesModule,
    networkingModule,

    MainFlow.module,
    GithubSearchFlow.module
)
