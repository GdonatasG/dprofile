package com.donatas.dprofile.composition.navigation.screens

import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.contacts.ContactsViewModel
import platform.UIKit.UIViewController

actual interface ContactsScreenFactory {
    fun controller(viewModel: ContactsViewModel, appTutorial: AppTutorial): UIViewController
}

actual class ContactsScreen actual constructor(
    private val factory: ContactsScreenFactory,
    private val viewModel: ContactsViewModel,
    private val appTutorial: AppTutorial
) : Screen {
    override fun controller(): UIViewController {
        return factory.controller(viewModel = viewModel, appTutorial = appTutorial)
    }
}
