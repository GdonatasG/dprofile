package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeViewModel
import org.koin.core.scope.Scope

expect interface AboutMeScreenFactory

expect class AboutMeScreen constructor(
    scope: Scope,
    factory: AboutMeScreenFactory,
    appTutorial: AppTutorial
) : Screen
