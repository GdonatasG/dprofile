package com.donatas.dprofile.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = AppDarkColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes
    ) {
        content()
    }
}
