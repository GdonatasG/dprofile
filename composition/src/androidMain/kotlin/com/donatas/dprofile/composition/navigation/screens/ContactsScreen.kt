package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.contacts.ContactsViewModel

actual interface ContactsScreenFactory {
    @Composable
    fun Compose(viewModel: ContactsViewModel, appTutorial: AppTutorial)
}

actual class ContactsScreen actual constructor(
    private val factory: ContactsScreenFactory,
    private val viewModel: ContactsViewModel,
    private val appTutorial: AppTutorial
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = viewModel, appTutorial = appTutorial)
    }
}
