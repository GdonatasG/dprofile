package com.donatas.dprofile.paginator.state

import com.donatas.dprofile.utils.generateUUID

sealed class PaginatorState {
    object Idle : PaginatorState()
    object Loading : PaginatorState()
    data class Error(val uuid: String = generateUUID(), val title: String, val message: String): PaginatorState()
}
