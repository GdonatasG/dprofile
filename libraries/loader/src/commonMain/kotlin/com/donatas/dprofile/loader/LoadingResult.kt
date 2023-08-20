package com.donatas.dprofile.loader

sealed class LoadingResult<out Item : Any> {
    data class Data<out Item : Any>(val data: List<Item>, val total: Int) : LoadingResult<Item>()
    data class Empty(val title: String, val message: String) : LoadingResult<Nothing>()
    data class Error(val title: String, val message: String) : LoadingResult<Nothing>()
}
