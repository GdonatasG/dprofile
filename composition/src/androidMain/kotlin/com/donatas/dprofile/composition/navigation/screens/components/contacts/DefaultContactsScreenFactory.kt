package com.donatas.dprofile.composition.navigation.screens.components.contacts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.composition.AppTutorial
import com.donatas.dprofile.composition.components.Message
import com.donatas.dprofile.composition.navigation.screens.ContactsScreenFactory
import com.donatas.dprofile.features.contacts.ContactsViewModel

class DefaultContactsScreenFactory : ContactsScreenFactory {
    @Composable
    override fun Compose(viewModel: ContactsViewModel, appTutorial: AppTutorial) {
        val tutorialState by appTutorial.state.collectAsState()

        println("rerender")

        AppScaffold(
            appBar = {
                DAppBar(title = "Contacts")
            }
        ) {
            if (tutorialState.step == 8) {
                Box(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp)
                ) {
                    Message(
                        message = "Finally, you have almost completed the journey through my application. " +
                                "In the end, I would like to share with you my contact information. \n" +
                                "\n" +
                                "Did you find something interesting about me? Have any questions? " +
                                "Or maybe you have some mobile application or another IT idea that you want to realize? Then reach out to me!\n" +
                                "\n" +
                                "Thank you again for downloading my application!\n" +
                                "\n" +
                                "Best Regards,\n" +
                                "\n" +
                                "Donatas"
                    )
                }

                return@AppScaffold
            }
            if (tutorialState.isFinished || tutorialState.step == 9) {
                ContactsView(viewModel)
            }
        }
    }
}
