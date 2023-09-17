package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.skills.SkillsViewModel
import org.koin.core.scope.Scope

expect interface SkillsScreenFactory

expect class SkillsScreen constructor(
    scope: Scope,
    factory: SkillsScreenFactory
) : Screen
