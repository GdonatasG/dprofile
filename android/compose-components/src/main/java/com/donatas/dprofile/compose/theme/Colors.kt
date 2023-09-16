package com.donatas.dprofile.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xFF64aba2)
val onPrimaryColor = Color(0xFFE4E4E4)
val onPrimaryColorDark = Color(0xFF3C4856)
val secondaryColor = Color(0xFFfafafa)
val secondaryColor1 = Color(0xFFE6F1FF)
val secondaryVariant = Color(0xFF7f8f9f)
val black700 = Color(0xFF434343)
val lightGreen = Color(0xFF6caf8f)
val lightRed = Color(0xFFbc5265)
val primaryColorDark = Color(0xFF64aba2)
val onBackgroundColorDark = Color(0xFFEEEEEE)
val backgroundColorDark = Color(0xFF212429)
val surfaceDarkColor = Color(0xFF373D44)
val backgroundSecondaryColor = Color(0xFFF1F2F4)
val tertiary = Color(0xFF6DB9B0)
val errorColor = Color(0xFFD32F2F)
val errorDarkColor = Color(0xFFbc5265)
val secondaryTextColorLight = Color(0xFFABB0B1)
val secondaryTextColorDark = Color(0xFFABB0B1)
val outlineLightColor = Color(0xFFABB0B1)
val outlineDarkColor = Color(0xFFABB0B1)

val AppDarkColorScheme = darkColorScheme(
    primary = primaryColorDark,
    onPrimary = onPrimaryColorDark,
    primaryContainer = backgroundColorDark,
    onPrimaryContainer = onBackgroundColorDark,
    inversePrimary = Color.White,
    secondary = black700,
    onSecondary = onBackgroundColorDark,
    secondaryContainer = backgroundColorDark,
    onSecondaryContainer = onBackgroundColorDark,
    tertiary = tertiary,
    onTertiary = Color.White,
    tertiaryContainer = backgroundColorDark,
    onTertiaryContainer = onBackgroundColorDark,
    background = backgroundColorDark,
    onBackground = onBackgroundColorDark,
    surface = surfaceDarkColor,
    onSurface = onBackgroundColorDark,
    surfaceVariant = backgroundColorDark,
    onSurfaceVariant = onBackgroundColorDark,
    surfaceTint = Color.Black,
    inverseSurface = Color(0xFF282c30), /* SnackBar */
    inverseOnSurface = Color.White, /* SnackBar */
    error = errorDarkColor,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    outline = outlineDarkColor,
    outlineVariant = secondaryVariant,
    scrim = Color.White
)

@Composable
fun getSecondaryTextColor(): Color = if (isSystemInDarkTheme()) secondaryTextColorDark else secondaryTextColorLight

