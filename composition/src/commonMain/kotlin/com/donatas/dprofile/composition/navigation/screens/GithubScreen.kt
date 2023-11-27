package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.GithubViewModel
import org.koin.core.scope.Scope

expect interface GithubScreenFactory

expect class GithubScreen constructor(
    scope: Scope,
    factory: GithubScreenFactory,
    appTutorial: AppTutorial
) : Screen
