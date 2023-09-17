package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.experience.ExperienceViewModel
import org.koin.core.scope.Scope

expect interface ExperienceScreenFactory

expect class ExperienceScreen constructor(
    scope: Scope,
    factory: ExperienceScreenFactory
) : Screen
