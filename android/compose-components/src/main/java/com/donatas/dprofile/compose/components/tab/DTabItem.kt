package com.donatas.dprofile.compose.components.tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.donatas.dprofile.compose.components.chip.DChip
import com.donatas.dprofile.compose.components.chip.DChipTextStyle

@Composable
fun DTabItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    label: String
) {
    val tabColor: Color by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.White.copy(alpha = 0.05f),
        animationSpec = tween(durationMillis = 100, easing = LinearEasing)
    )

    DChip(
        backgroundColor = tabColor,
        onClick = onClick
    ) {
        Text(
            text = label,
            maxLines = 1,
            style = DChipTextStyle()
        )
    }
}
