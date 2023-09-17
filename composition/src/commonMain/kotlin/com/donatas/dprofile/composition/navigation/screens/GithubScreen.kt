package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.GithubViewModel

expect interface GithubScreenFactory

expect class GithubScreen constructor(
    factory: GithubScreenFactory,
    viewModel: GithubViewModel
) : Screen
