package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.AboutMeViewModel

expect interface AboutMeScreenFactory

expect class AboutMeScreen constructor(
    factory: AboutMeScreenFactory,
    viewModel: AboutMeViewModel
) : Screen
