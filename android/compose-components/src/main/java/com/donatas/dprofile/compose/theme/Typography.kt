package com.donatas.dprofile.compose.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DefaultTextStyle = TextStyle(
    fontFamily = SpaceGrotesk,
    fontWeight = FontWeight.Light,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
)

val AppTypography = Typography(
    displayLarge = DefaultTextStyle.copy(fontSize = 57.sp, lineHeight = 64.sp),
    displayMedium = DefaultTextStyle.copy(fontSize = 45.sp, lineHeight = 52.sp),
    displaySmall = DefaultTextStyle.copy(fontSize = 36.sp, lineHeight = 44.sp),
    headlineLarge = DefaultTextStyle.copy(fontSize = 32.sp, lineHeight = 40.sp),
    headlineMedium = DefaultTextStyle.copy(fontSize = 28.sp, lineHeight = 36.sp),
    headlineSmall = DefaultTextStyle.copy(fontSize = 24.sp, lineHeight = 32.sp),
    titleLarge = DefaultTextStyle.copy(fontSize = 22.sp, lineHeight = 28.sp, fontWeight = FontWeight.Medium),
    titleMedium = DefaultTextStyle.copy(fontSize = 16.sp, lineHeight = 24.sp, fontWeight = FontWeight.Medium),
    titleSmall = DefaultTextStyle.copy(fontSize = 13.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium),
    bodyLarge = DefaultTextStyle.copy(fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = DefaultTextStyle.copy(fontSize = 14.sp, lineHeight = 20.sp),
    bodySmall = DefaultTextStyle.copy(fontSize = 12.sp, lineHeight = 16.sp),
    labelLarge = DefaultTextStyle.copy(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium),
    labelMedium = DefaultTextStyle.copy(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.Medium),
    labelSmall = DefaultTextStyle.copy(fontSize = 11.sp, lineHeight = 16.sp, fontWeight = FontWeight.Medium)
)
