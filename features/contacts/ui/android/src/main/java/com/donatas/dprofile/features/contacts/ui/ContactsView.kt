package com.donatas.dprofile.features.contacts.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.features.contacts.presentation.ContactsViewModel

@Composable
fun ContactsView(model: ContactsViewModel) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            ElevatedButton(onClick = model::onEmail) {
                Text(text = "Email")
            }
            ElevatedButton(onClick = model::onLinkedIn) {
                Text(text = "LinkedIn")
            }
            ElevatedButton(onClick = model::onUpwork) {
                Text(text = "Upwork")
            }
            ElevatedButton(onClick = model::onFreelancer) {
                Text(text = "Freelancer")
            }
        }
    }
}
