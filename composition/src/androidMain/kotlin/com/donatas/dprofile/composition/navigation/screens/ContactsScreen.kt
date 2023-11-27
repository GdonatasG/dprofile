package com.donatas.dprofile.composition.navigation.screens

import androidx.compose.runtime.Composable
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.di.extensions.getNavViewModel
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.contacts.ContactsViewModel
import org.koin.core.scope.Scope

actual interface ContactsScreenFactory {
    @Composable
    fun Compose(viewModel: ContactsViewModel, appTutorial: AppTutorial)
}

actual class ContactsScreen actual constructor(
    private val scope: Scope,
    private val factory: ContactsScreenFactory,
    private val appTutorial: AppTutorial
) : Screen {
    @Composable
    override fun Compose() {
        factory.Compose(viewModel = getNavViewModel(scope = scope), appTutorial = appTutorial)
    }
}
