package com.donatas.dprofile.loader.state

sealed class ListState<out Item : Any> {
    data class Data<out Item : Any>(
        val data: List<Item>,
        val total: Int,
        val isFirstPage: Boolean
    ) : ListState<Item>()

    data class Empty(val title: String, val message: String? = null) : ListState<Nothing>()

    object Loading : ListState<Nothing>()

    data class Error(val title: String, val message: String? = null) : ListState<Nothing>()
}
