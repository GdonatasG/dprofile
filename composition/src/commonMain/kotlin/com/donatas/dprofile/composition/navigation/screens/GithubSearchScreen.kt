package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import org.koin.core.scope.Scope

expect interface GithubSearchScreenFactory

expect class GithubSearchScreen constructor(
    scope: Scope,
    factory: GithubSearchScreenFactory
) : Screen
