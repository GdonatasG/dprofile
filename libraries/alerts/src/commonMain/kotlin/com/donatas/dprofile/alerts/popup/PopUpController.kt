package com.donatas.dprofile.alerts.popup

import kotlinx.coroutines.flow.StateFlow

abstract class PopUpController {
    abstract val popUp: StateFlow<PopUp?>

    abstract suspend fun show(popUp: PopUp)
    abstract fun dismiss()
}
