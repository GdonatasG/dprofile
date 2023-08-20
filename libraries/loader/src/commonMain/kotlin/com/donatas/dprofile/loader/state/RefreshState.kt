package com.donatas.dprofile.loader.state

import com.donatas.dprofile.utils.generateUUID

sealed class RefreshState {
    object Idle : RefreshState()
    object Refreshing : RefreshState()
    data class Error(val uuid: String = generateUUID(), val title: String, val message: String) : RefreshState()
}
