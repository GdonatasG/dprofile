package com.donatas.dprofile.composition.navigation.screens.components.contacts

import androidx.compose.runtime.Composable
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.features.contacts.ContactsViewModel

class DefaultContactsScreenFactory: ContactsScreenFactory {
    @Composable
    override fun Compose(viewModel: ContactsViewModel) {
        AppScaffold(
            appBar = {
                DAppBar(title = "Contacts")
            }
        ) {
            ContactsView(viewModel)
        }
    }
}
