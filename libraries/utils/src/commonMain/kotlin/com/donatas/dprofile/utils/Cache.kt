package com.donatas.dprofile.utils

interface Cache<Item> {
    fun save(value: Item)

    @Throws(Empty::class)
    fun get(): Item

    fun clear()

    fun isEmpty(): Boolean

    class Empty : Exception("Cache is empty!")
}
