package com.donatas.dprofile.features.filter.shared.observable

import com.donatas.dprofile.features.filter.shared.FilterStore
import com.donatas.dprofile.features.filter.shared.FilterValue
import com.donatas.dprofile.utils.observer.Observable
import com.donatas.dprofile.utils.observer.Observer

class AppliedFilters(
    val filters: List<FilterValue>,
    /** Indicated whether update is required, such as requesting new data from API **/
    val updateRequired: Boolean
)

class AppliedFiltersObservable(
    initial: FilterStore, private val filterStoreObservable: Observable<FilterStore>
) : Observable<AppliedFilters>, Observer<FilterStore> {
    override val observers: MutableSet<Observer<AppliedFilters>> = mutableSetOf()

    private var store: FilterStore = initial

    override fun update(value: FilterStore) {
        notifyObservers(
            AppliedFilters(
                filters = value.getSelected(), updateRequired = store != value
            )
        )

        store = value
    }

    override fun add(observer: Observer<AppliedFilters>) {
        super.add(observer)
        if (observers.size == 1) {
            filterStoreObservable.add(this)
        }
    }

    override fun remove(observer: Observer<AppliedFilters>) {
        super.remove(observer)
        if (observers.isEmpty()) {
            filterStoreObservable.remove(this)
        }
    }
}
