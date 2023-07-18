package com.donatas.dprofile.utils.observer

interface Observer<T> {
    fun update(value: T)
}
