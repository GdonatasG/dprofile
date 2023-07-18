package com.donatas.dprofile.composition.presentation.navigation

import com.donatas.dprofile.alerts.Alert

internal interface AlertState {
    fun setAlert(alert: Alert)
    fun getTitle(): String
    fun getMessage(): String
    fun getButtons(): List<Alert.Button>
}

internal class AlertStateAdapter : AlertState {
    private var alert: Alert? = null
    override fun setAlert(alert: Alert) {
        this.alert = alert
    }

    override fun getTitle(): String = alert?.title ?: ""

    override fun getMessage(): String = alert?.message ?: ""

    override fun getButtons(): List<Alert.Button> = alert?.buttons ?: emptyList()
}
