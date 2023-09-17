package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.features.filter.FiltersViewModel
import platform.UIKit.UIViewController

actual interface FiltersModalFactory {
    fun controller(viewModel: FiltersViewModel): UIViewController
}

actual class FiltersModal actual constructor(
    private val factory: FiltersModalFactory,
    private val viewModel: FiltersViewModel
) : Modal {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = viewModel)
    }
}
