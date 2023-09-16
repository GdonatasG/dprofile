package com.donatas.dprofile.composition.presentation.alert

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.composition.presentation.alert.AlertState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal interface AlertController {
    val navigationAction: StateFlow<Unit?>
    val closeAlert: StateFlow<Unit?>

    fun close()
    fun show(alert: Alert)
    fun resetNavigationAction()
    fun resetCloseAction()
}

internal class AlertControllerAdapter(
    private val alertState: AlertState
) : AlertController {
    private val _navigationAction: MutableStateFlow<Unit?> = MutableStateFlow(null)
    private val _closeAction: MutableStateFlow<Unit?> = MutableStateFlow(null)

    override val navigationAction: StateFlow<Unit?> get() = _navigationAction.asStateFlow()
    override val closeAlert: StateFlow<Unit?> get() = _closeAction.asStateFlow()

    override fun close() {
        _closeAction.value = Unit
    }

    override fun show(alert: Alert) {
       alertState.setAlert(alert)
        _navigationAction.value = Unit
    }

    override fun resetNavigationAction() {
        _navigationAction.value = null
    }

    override fun resetCloseAction() {
        _closeAction.value = null
    }
}
