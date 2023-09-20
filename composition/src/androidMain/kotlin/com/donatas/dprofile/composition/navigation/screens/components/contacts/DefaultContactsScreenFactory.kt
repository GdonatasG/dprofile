package com.donatas.dprofile.composition.navigation.screens.components.contacts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.features.contacts.ContactsViewModel

class DefaultContactsScreenFactory : ContactsScreenFactory {
    @Composable
    override fun Compose(viewModel: ContactsViewModel, appTutorial: AppTutorial) {
        val tutorialState by appTutorial.state.collectAsState()

        AppScaffold(
            appBar = {
                DAppBar(title = "Contacts")
            }
        ) {
            if (tutorialState.step == 8) {
                Text(text = "Some tutorial message")

                return@AppScaffold
            }
            if (tutorialState.isFinished || tutorialState.step == 9) {
                ContactsView(viewModel)
            }
        }
    }
}
