package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingViewModel
import org.koin.core.scope.Scope

expect interface RoadToProgrammingScreenFactory

expect class RoadToProgrammingScreen constructor(
    scope: Scope,
    factory: RoadToProgrammingScreenFactory
) : Screen
