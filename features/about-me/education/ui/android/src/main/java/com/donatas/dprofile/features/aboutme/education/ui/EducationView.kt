package com.donatas.dprofile.features.aboutme.education.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.ZeroCornerSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.donatas.dprofile.compose.components.timeline.TimelineNode
import com.donatas.dprofile.compose.theme.secondaryTextColorDark
import com.donatas.dprofile.compose.theme.secondaryTextColorLight
import com.donatas.dprofile.features.aboutme.education.presentation.EducationViewModel
import com.donatas.dprofile.features.aboutme.education.presentation.TimelineItem
import kotlinx.coroutines.delay

@Composable
fun EducationView(model: EducationViewModel) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        model.timeline.forEachIndexed { index, timelineItem ->
            TimelineNode { modifier ->
                TimelineItemContent(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        ),
                    item = timelineItem,
                    index = index
                )
            }
        }
    }
}

@Composable
fun TimelineItemContent(
    modifier: Modifier,
    item: TimelineItem,
    index: Int
) {
    val animationDelayInMillis: Long = 300L * index

    var visible by remember { mutableStateOf(item.animated) }

    LaunchedEffect(true) {
        if (!visible) {
            delay(animationDelayInMillis)
            visible = true
            item.animated = true
        }
    }

    val secondaryColor = if (isSystemInDarkTheme()) secondaryTextColorDark else secondaryTextColorLight
    val titleTextStyle = MaterialTheme.typography.titleSmall.copy(color = secondaryColor, fontSize = 14.sp)
    val subtitleTextStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.primary)
    val descriptionTextStyle = MaterialTheme.typography.bodyMedium
    val locationTextStyle = MaterialTheme.typography.labelMedium.copy(color = secondaryColor)

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandIn()
    ) {
        Card(
            modifier = modifier,
            shape = MaterialTheme.shapes.small.copy(topStart = ZeroCornerSize),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.05f)
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = item.title, style = titleTextStyle)
                Text(text = item.subtitle, style = subtitleTextStyle)
                Text(text = item.description, style = descriptionTextStyle)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "location",
                        tint = locationTextStyle.color
                    )
                    Text(text = item.location, style = locationTextStyle)
                }
            }
        }
    }
}
