package com.donatas.dprofile.alerts.popup

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.job
import kotlin.coroutines.coroutineContext

class DefaultPopUpController : PopUpController() {
    private var _popUp: MutableStateFlow<PopUp?> = MutableStateFlow(null)
    override val popUp: StateFlow<PopUp?> = _popUp.asStateFlow()

    private var job: Job? = null

    override suspend fun show(popUp: PopUp) {
        dismiss()

        job = coroutineContext.job

        _popUp.value = popUp
        delay(popUp.dismissAfterInMillis)
        dismiss()
    }

    override fun dismiss() {
        job?.cancel()
        job = null
        _popUp.value = null
    }
}
