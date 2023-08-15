package com.donatas.dprofile.features.contacts

import androidx.compose.runtime.Composable
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.contacts.presentation.ContactsViewModel
import com.donatas.dprofile.features.contacts.ui.ContactsView
import org.koin.androidx.compose.getViewModel

actual class ContactsScreen actual constructor() : Screen {
    @Composable
    override fun Compose() {
        val viewModel: ContactsViewModel = getViewModel<ContactsViewModel>()
        AppScaffold(
            appBar = {
                DAppBar(title = "Contacts")
            }
        ) {
            ContactsView(viewModel)
        }
    }
}
