package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import org.koin.core.scope.Scope

expect interface EducationScreenFactory

expect class EducationScreen constructor(
    scope: Scope,
    factory: EducationScreenFactory
) : Screen
