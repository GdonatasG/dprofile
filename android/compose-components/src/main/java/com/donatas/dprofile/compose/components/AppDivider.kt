package com.donatas.dprofile.compose.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.theme.secondaryTextColorDark
import com.donatas.dprofile.compose.theme.secondaryTextColorLight

@Composable
fun AppDivider(modifier: Modifier = Modifier) {
    val secondaryTextColor: Color = if (isSystemInDarkTheme()) secondaryTextColorDark else secondaryTextColorLight

    Divider(
        modifier = modifier,
        color = secondaryTextColor,
        thickness = 0.25.dp
    )
}
