package com.donatas.dprofile.compose.components.button

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.theme.secondaryTextColorDark
import com.donatas.dprofile.compose.theme.secondaryTextColorLight

@Composable
fun ActionButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    val contentColor = if (isSystemInDarkTheme()) secondaryTextColorDark else secondaryTextColorLight

    val colors = IconButtonDefaults.iconButtonColors(
        contentColor = contentColor
    )

    IconButton(
        modifier = Modifier
            .size(48.dp)
            .padding(4.dp),
        onClick = onClick,
        colors = colors
    ) {
        icon()
    }
}
