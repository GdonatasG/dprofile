package com.donatas.dprofile.compose.components.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun DChip(
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    onClick: (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit
) {

    var modifier = Modifier
        .background(
            color = backgroundColor, shape = MaterialTheme.shapes.small
        )
        .clip(MaterialTheme.shapes.small)

    onClick?.let {
        modifier = modifier.clickable { it() }
    }

    modifier = modifier.padding(
        vertical = 8.dp, horizontal = 12.dp
    )

    Row(
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = modifier
    ) {
        content()
    }
}

@Composable
fun DChipTextStyle(): TextStyle = MaterialTheme.typography.titleSmall
