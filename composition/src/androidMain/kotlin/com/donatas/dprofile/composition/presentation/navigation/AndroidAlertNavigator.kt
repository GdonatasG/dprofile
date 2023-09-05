package com.donatas.dprofile.composition.presentation.navigation

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.composition.presentation.alert.AlertController

internal class AndroidAlertNavigator(
    private val alert: AlertController
) : Alert.Coordinator {
    override fun show(alert: Alert) {
        this.alert.show(alert)
    }

    override fun dismiss() {
        this.alert.close()
    }
}
