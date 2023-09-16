package com.donatas.dprofile.composition

import com.donatas.dprofile.alerts.Alert
import com.donatas.dprofile.composition.di.UINavigationControllerWrapper
import com.donatas.dprofile.composition.navigation.core.Navigator
import com.donatas.dprofile.feature.Modal
import com.donatas.dprofile.feature.Screen
import platform.UIKit.*

internal class UIKitNavigator(
    private val controller: UINavigationControllerWrapper
) : Navigator {
    override fun set(screen: Screen) {
        controller.shared.setViewControllers(listOf(screen.controller()), true)
    }

    override fun push(screen: Screen) {
        controller.shared.pushViewController(listOf(screen.controller()), true)
    }

    override fun push(modal: Modal) {
        TODO("Not yet implemented")
    }

    override fun pop() {
        controller.shared.popViewControllerAnimated(true)
    }
}

internal class UIKitAlertCoordinator(
    private val controller: UINavigationControllerWrapper
) : Alert.Coordinator {
    override fun show(alert: Alert) {
        val dialog = UIAlertController.alertControllerWithTitle(
            title = alert.title,
            message = alert.message,
            preferredStyle = UIAlertControllerStyleAlert
        )

        alert.buttons.forEach { button ->
            val action = UIAlertAction.actionWithTitle(
                title = button.title,
                style = button.event.toUIKitStyle(),
                handler = {
                    alert.send(button.event)
                }
            )
            dialog.addAction(action)
        }

        controller.shared.topViewController?.presentViewController(dialog, animated = true, completion = null)
    }

    private fun Alert.Button.Event.toUIKitStyle(): UIAlertActionStyle {
        return when (this) {
            Alert.Button.Event.CANCEL -> UIAlertActionStyleCancel
            Alert.Button.Event.DEFAULT -> UIAlertActionStyleDefault
            Alert.Button.Event.DESTRUCTIVE -> UIAlertActionStyleDestructive
        }
    }
}
