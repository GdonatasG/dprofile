package com.donatas.dprofile.features.filter

import com.donatas.dprofile.features.filter.presentation.AddFilterStoreObserver
import com.donatas.dprofile.features.filter.presentation.ApplyFilters
import com.donatas.dprofile.features.filter.presentation.FilterViewModel
import com.donatas.dprofile.features.filter.presentation.FiltersDelegate
import com.donatas.dprofile.features.filter.presentation.GetFilterStore
import com.donatas.dprofile.features.filter.presentation.RemoveFilterStoreObserver
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    viewModel<FilterViewModel>() {
        FilterViewModel(
            delegate = get<FiltersDelegate>(),
            addFilterStoreObserver = get<AddFilterStoreObserver>(),
            removeFilterStoreObserver = get<RemoveFilterStoreObserver>(),
            getFilterStore = get<GetFilterStore>(),
            applyFilters = get<ApplyFilters>()
        )
    }
}
