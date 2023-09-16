package com.donatas.dprofile.composition.presentation.alert

import com.donatas.dprofile.alerts.Alert

internal class AlertCoordinator(private val alertController: AlertController) : Alert.Coordinator {
    override fun show(alert: Alert) {
        alertController.show(alert)
    }
}
