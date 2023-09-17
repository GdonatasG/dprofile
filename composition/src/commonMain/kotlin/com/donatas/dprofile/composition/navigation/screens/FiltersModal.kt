package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.features.filter.FiltersViewModel

expect interface FiltersModalFactory

expect class FiltersModal constructor(
    factory: FiltersModalFactory,
    viewModel: FiltersViewModel
) : Modal
