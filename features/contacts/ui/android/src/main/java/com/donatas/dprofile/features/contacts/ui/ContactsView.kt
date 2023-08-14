package com.donatas.dprofile.features.contacts.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.theme.secondaryTextColorDark
import com.donatas.dprofile.compose.theme.secondaryTextColorLight
import com.donatas.dprofile.features.contacts.presentation.ContactsViewModel
import kotlinx.coroutines.delay

@Composable
fun ContactsView(model: ContactsViewModel) {
    val imageBorderColor: Color = if (isSystemInDarkTheme()) secondaryTextColorDark else secondaryTextColorLight

    var scaleStarted by remember { mutableStateOf(false) }
    var scaleFinished by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (scaleStarted) 1f else 0.2f, animationSpec = tween(800)
    )

    LaunchedEffect(true) {
        delay(50)
        scaleStarted = true
    }

    LaunchedEffect(scale) {
        scaleFinished = scale == 1f
    }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .scale(scale), elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(
                    state = rememberScrollState(),
                    enabled = scaleFinished
                )
                .padding(16.dp),
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
                text = "Donatas Žitkus",
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Mobile Applications Developer", style = MaterialTheme.typography.labelLarge
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
                    style = MaterialTheme.typography.labelLarge.copy(color = imageBorderColor)
                )
            }
        }
    }
}
