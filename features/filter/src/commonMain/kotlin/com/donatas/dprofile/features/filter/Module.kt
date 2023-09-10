package com.donatas.dprofile.features.filter

import com.donatas.dprofile.features.filter.presentation.AddFilterStoreObserver
import com.donatas.dprofile.features.filter.presentation.ApplyFilters
import com.donatas.dprofile.features.filter.presentation.FiltersDelegate
import com.donatas.dprofile.features.filter.presentation.GetFilterStore
import com.donatas.dprofile.features.filter.presentation.RemoveFilterStoreObserver
import com.donatas.dprofile.features.filter.shared.FilterStore
import com.donatas.dprofile.features.filter.shared.FilterValue
import com.donatas.dprofile.features.filter.shared.ParentData
import com.donatas.dprofile.features.filter.shared.model.SingleChoicePredefinedFilterModel
import com.donatas.dprofile.features.filter.shared.observable.FilterStoreObservableCache
import com.donatas.dprofile.utils.observer.Observer
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

internal fun loadModules(cache: FilterStoreObservableCache) = loadKoinModules(
    listOf(
        commonModule(cache),
        platformModule
    )
)

internal fun commonModule(cache: FilterStoreObservableCache) = module {
    single<FilterModal> {
        FilterModal()
    }

    single<FilterStoreObservableCache> {
        cache
    }

    single<AddFilterStoreObserver> {
        DefaultAddFilterStoreObserver(
            cache = get<FilterStoreObservableCache>()
        )
    }

    single<RemoveFilterStoreObserver> {
        DefaultRemoveFilterStoreObserver(
            cache = get<FilterStoreObservableCache>()
        )
    }

    single<GetFilterStore> {
        DefaultGetFilterStore(
            cache = get<FilterStoreObservableCache>()
        )
    }

    single<ApplyFilters> {
        DefaultApplyFilters(
            cache = get<FilterStoreObservableCache>()
        )
    }
}

internal expect val platformModule: Module

private class DefaultAddFilterStoreObserver(
    private val cache: FilterStoreObservableCache
) : AddFilterStoreObserver {
    override fun invoke(observer: Observer<FilterStore>) {
        cache.add(observer)
    }
}

private class DefaultRemoveFilterStoreObserver(
    private val cache: FilterStoreObservableCache
) : RemoveFilterStoreObserver {
    override fun invoke(observer: Observer<FilterStore>) {
        cache.remove(observer)
    }
}

private class DefaultGetFilterStore(
    private val cache: FilterStoreObservableCache
) : GetFilterStore {
    override fun invoke(): FilterStore = cache.get()
}

private class DefaultApplyFilters(
    private val cache: FilterStoreObservableCache
) : ApplyFilters {
    override fun invoke(filterStore: FilterStore) {
        cache.save(filterStore)
    }

}


