package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.features.filter.FiltersViewModel
import platform.AppKit.NSViewController

actual interface FiltersModalFactory {
    fun controller(viewModel: FiltersViewModel): NSViewController
}

actual class FiltersModal actual constructor(
    private val factory: FiltersModalFactory,
    private val viewModel: FiltersViewModel
) : Modal {
    override fun controller(): NSViewController {
        return factory.controller(viewModel = viewModel)
    }
}
