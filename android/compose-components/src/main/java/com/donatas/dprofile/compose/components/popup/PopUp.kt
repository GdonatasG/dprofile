package com.donatas.dprofile.compose.components.popup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun PopUp(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.extraSmall,
    containerColor: Color = MaterialTheme.colorScheme.inverseSurface,
    contentColor: Color = MaterialTheme.colorScheme.inverseOnSurface,
    content: @Composable () -> Unit
) {
    val textStyle = MaterialTheme.typography.bodyMedium

    val surfaceContent = @Composable {
        CompositionLocalProvider(LocalTextStyle provides textStyle) {
            content()
        }
    }

    if (onClick != null) {
        Surface(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .semantics { role = Role.Button },
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            shadowElevation = 6.dp
        ) {
            surfaceContent()
        }
    } else {
        Surface(
            modifier = modifier
                .fillMaxWidth(),
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            shadowElevation = 6.dp
        ) {
            surfaceContent()
        }
    }
}
