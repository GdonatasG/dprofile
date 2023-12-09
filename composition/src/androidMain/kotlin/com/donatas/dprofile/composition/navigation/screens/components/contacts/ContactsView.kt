package com.donatas.dprofile.composition.navigation.screens.components.contacts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.ModalDivider
import com.donatas.dprofile.compose.theme.secondaryTextColorDark
import com.donatas.dprofile.compose.theme.secondaryTextColorLight
import com.donatas.dprofile.composition.R
import com.donatas.dprofile.features.contacts.ContactsViewModel

@Composable
fun ContactsView(model: ContactsViewModel) {
    val imageBorderColor: Color = if (isSystemInDarkTheme()) secondaryTextColorDark else secondaryTextColorLight

    Card(
        modifier = Modifier
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(110.dp)
                        .border(BorderStroke(1.dp, imageBorderColor), CircleShape)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.face),
                    contentDescription = "Face"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Donatas Zitkus",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Mobile Applications Developer",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "location",
                        tint = imageBorderColor
                    )
                    Text(
                        text = "Kaunas, Lithuania",
                        style = MaterialTheme.typography.labelMedium.copy(color = imageBorderColor),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Column {
                ContactListTile(
                    icon = {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(id = R.drawable.email),
                            contentDescription = "Email",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }, title = "Email", trailingTitle = "Email me", divided = true, onClick = model::onEmail
                )
                ContactListTile(
                    icon = {
                        Image(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            painter = painterResource(id = R.drawable.linkedin),
                            contentDescription = "LinkedIn"
                        )
                    },
                    title = "LinkedIn",
                    trailingTitle = "Let's connect",
                    divided = true,
                    onClick = model::onLinkedIn
                )
                ContactListTile(
                    icon = {
                        Image(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            painter = painterResource(id = R.drawable.upwork),
                            contentDescription = "Upwork"
                        )
                    }, title = "Upwork", trailingTitle = "View profile", divided = true, onClick = model::onUpwork
                )
                ContactListTile(
                    icon = {
                        Image(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            painter = painterResource(id = R.drawable.freelancer),
                            contentDescription = "Freelancer"
                        )
                    },
                    title = "Freelancer",
                    trailingTitle = "View profile",
                    divided = false,
                    onClick = model::onFreelancer
                )
            }
        }
    }
}

@Composable
private fun ContactListTile(
    icon: @Composable () -> Unit, title: String, trailingTitle: String, divided: Boolean, onClick: () -> Unit
) {
    val secondaryTextColor: Color = if (isSystemInDarkTheme()) secondaryTextColorDark else secondaryTextColorLight

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(
                    horizontal = 16.dp, vertical = 12.dp
                ), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.weight(1f, fill = true),
                text = trailingTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelMedium.copy(color = secondaryTextColor),
            )
            Icon(
                imageVector = Icons.Default.ChevronRight, contentDescription = trailingTitle, tint = secondaryTextColor
            )
        }
        if (divided) {
            ModalDivider(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }

}
