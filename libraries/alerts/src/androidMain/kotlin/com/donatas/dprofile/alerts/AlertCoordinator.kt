package com.donatas.dprofile.alerts

class AlertCoordinator(private val alertController: AlertController) : Alert.Coordinator {
    override fun show(alert: Alert) {
        alertController.show(alert)
    }
}
