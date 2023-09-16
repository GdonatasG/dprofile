package com.donatas.dprofile.features.filter.shared

data class FilterData(
    val id: String,
    val name: String,
    val description: String? = null,
    var selected: Boolean = false
)
