package com.donatas.dprofile.features.aboutme.experience.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.theme.secondaryTextColorDark
import com.donatas.dprofile.compose.theme.secondaryTextColorLight
import com.donatas.dprofile.features.aboutme.experience.presentation.ExperienceViewModel

@Composable
fun ExperienceView(
    model: ExperienceViewModel
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TimelineNode {
            Box(
                modifier = it
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(bottom = 16.dp)
                    .background(Color.Red)
            )
        }
        TimelineNode {
            Box(
                modifier = it
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Green)
            )
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
    val secondaryColor = if (isSystemInDarkTheme()) secondaryTextColorDark else secondaryTextColorLight
    val titleTextStyle = MaterialTheme.typography.titleSmall.copy(color = secondaryColor)
    val subtitleTextStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.primary)
    val descriptionTextStyle = MaterialTheme.typography.labelSmall
    val locationTextStyle = MaterialTheme.typography.labelSmall.copy(color = secondaryColor)

    BoxWithConstraints(modifier = Modifier
        .fillMaxWidth()
        .drawBehind {
            drawLine(
                color = lineColor,
                start = Offset(circleRadiusInPx, (circleRadiusInPx * 2) + circleRadiusInPx),
                end = Offset(circleRadiusInPx, this.size.height - circleRadiusInPx),
                strokeWidth = 2.dp.toPx(),
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
        }) {
        content(
            Modifier.padding(
                    top = circleRadius / 2, start = circleRadius * 2 + 16.dp
                )
        )
    }
}

data class TimelineItem(
    val title: String, val subtitle: String, val description: String, val location: String, val icon: ImageVector
)
