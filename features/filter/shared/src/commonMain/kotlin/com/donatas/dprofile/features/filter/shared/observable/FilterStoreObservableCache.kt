package com.donatas.dprofile.features.filter.shared.observable

import com.donatas.dprofile.features.filter.shared.FilterStore
import com.donatas.dprofile.utils.Cache
import com.donatas.dprofile.utils.observer.Observable
import com.donatas.dprofile.utils.observer.Observer

class FilterStoreObservableCache(
    initial: FilterStore
) : Cache<FilterStore>, Observable<FilterStore> {
    private var _store: FilterStore = initial

    override val observers: MutableSet<Observer<FilterStore>> = mutableSetOf()

    override fun save(value: FilterStore) {
        if (_store == value) return

        _store = value
        notifyObservers(_store)
    }

    override fun get(): FilterStore = _store

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun add(observer: Observer<FilterStore>) {
        super.add(observer)
        observer.update(_store)
    }

}
