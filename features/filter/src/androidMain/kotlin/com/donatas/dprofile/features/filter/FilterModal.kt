package com.donatas.dprofile.features.filter

import androidx.compose.runtime.Composable
import com.donatas.dprofile.compose.components.layout.ModalScaffold
import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.features.filter.presentation.FilterViewModel
import com.donatas.dprofile.features.filter.ui.FilterView
import org.koin.androidx.compose.getViewModel

actual class FilterModal actual constructor() : Modal {
    @Composable
    override fun Compose() {
        val viewModel: FilterViewModel = getViewModel<FilterViewModel>()
        ModalScaffold {
            FilterView(viewModel)
        }
    }
}
