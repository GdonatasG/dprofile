package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.features.filter.FiltersViewModel

actual interface FiltersModalFactory {
    @Composable
    fun Compose(viewModel: FiltersViewModel)
}

actual class FiltersModal actual constructor(
    private val factory: FiltersModalFactory,
    private val viewModel: FiltersViewModel
) : Modal {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = viewModel)
    }
}
