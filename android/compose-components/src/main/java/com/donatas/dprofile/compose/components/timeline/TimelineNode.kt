package com.donatas.dprofile.compose.components.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
