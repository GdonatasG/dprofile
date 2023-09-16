package com.donatas.dprofile.features.filter.presentation

import com.donatas.dprofile.features.filter.shared.FilterStore
import com.donatas.dprofile.utils.observer.Observer
import com.donatas.dprofile.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FilterViewModel(
    private val delegate: FiltersDelegate,
    private val addFilterStoreObserver: AddFilterStoreObserver,
    private val removeFilterStoreObserver: RemoveFilterStoreObserver,
    getFilterStore: GetFilterStore,
    private val applyFilters: ApplyFilters
) : ViewModel() {
    private var localStore: FilterStore? = null
    private var originalStore: FilterStore = FilterStore.empty()

    private var _filterStore: MutableStateFlow<FilterStore> = MutableStateFlow(FilterStore.empty())
    val filterStore: StateFlow<FilterStore> = _filterStore.asStateFlow()

    init {
        originalStore = getFilterStore()
        val initialStore = originalStore.deepCopy()
        localStore = initialStore
        _filterStore.update { initialStore }
    }

    override fun onAppear() {
        super.onAppear()
        addFilterStoreObserver(filterStoreObserver)
    }

    private val filterStoreObserver = object : Observer<FilterStore> {
        override fun update(value: FilterStore) {
            if (localStore != null) return
            if (localStore == value) return

            originalStore = value
            localStore = value.deepCopy()
            localStore?.let { store ->
                _filterStore.update { store }
            }
        }
    }

    fun onResetFilters(){
        _filterStore.value.data.forEach { filter ->
            filter.onReset()
        }
    }

    fun onApply(){
        applyFilters(_filterStore.value)
        delegate.onBack()
    }

    override fun onDisappear() {
        super.onDisappear()
        removeFilterStoreObserver(filterStoreObserver)
    }




}
