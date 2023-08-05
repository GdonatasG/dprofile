package com.donatas.dprofile.features.aboutme.experience.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.School
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.theme.secondaryTextColorDark
import com.donatas.dprofile.compose.theme.secondaryTextColorLight
import com.donatas.dprofile.features.aboutme.experience.presentation.ExperienceViewModel
import com.donatas.dprofile.features.aboutme.experience.presentation.TimelineItem
import kotlinx.coroutines.delay

@Composable
fun ExperienceView(
    model: ExperienceViewModel
) {
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
fun TimelineNode(content: @Composable BoxScope.(modifier: Modifier) -> Unit) {
    val density = LocalDensity.current
    val circleRadius: Dp = 10.dp
    val circleStrokeRadius: Dp = 4.dp
    val circleRadiusInPx = with(density) { circleRadius.toPx() }
    val circleStrokeRadiusInPx = with(density) { circleStrokeRadius.toPx() }
    val lineColor: Color = Color.White.copy(alpha = 0.1f)
    val circleColor: Color = Color.Transparent
    val circleStrokeColor: Color = MaterialTheme.colorScheme.primary

    val paddingTop = circleRadius / 2
    val paddingBottom = circleRadius

    val verticalPaddingSizeInPx = with(density) { (paddingTop + paddingBottom).toPx() }

    BoxWithConstraints(modifier = Modifier
        .fillMaxWidth()
        .drawBehind {
            if (this.size.height > verticalPaddingSizeInPx) {
                drawLine(
                    color = lineColor,
                    start = Offset(circleRadiusInPx, (circleRadiusInPx * 2) + circleRadiusInPx),
                    end = Offset(
                        circleRadiusInPx, this.size.height - circleRadiusInPx
                    ),
                    strokeWidth = 1.5.dp.toPx(),
                )
                drawCircle(
                    circleColor, circleRadiusInPx, center = Offset(circleRadiusInPx, circleRadiusInPx)
                )

                drawCircle(
                    color = circleStrokeColor,
                    radius = circleRadiusInPx - circleStrokeRadiusInPx / 2,
                    center = Offset(x = circleRadiusInPx, y = circleRadiusInPx),
                    style = Stroke(width = circleStrokeRadiusInPx)
                )
            }
        }) {
        Box(
            modifier = Modifier
                .padding(
                    start = circleRadius * 2,
                    top = paddingTop,
                    bottom = paddingBottom
                )
        ) {
            content(Modifier)
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
    val titleTextStyle = MaterialTheme.typography.titleMedium.copy(color = secondaryColor)
    val subtitleTextStyle = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.primary)
    val descriptionTextStyle = MaterialTheme.typography.bodyMedium
    val locationTextStyle = MaterialTheme.typography.labelLarge.copy(color = secondaryColor)

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
