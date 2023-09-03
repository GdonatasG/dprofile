package com.donatas.dprofile.compose.components.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun FullWidthButton(
    modifier: Modifier = Modifier,
    title: String,
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    val enabled = isEnabled && !isLoading

    val disabledAlpha: Float = 0.7f

    Button(
        onClick = {
            focusManager.clearFocus(true)
            onClick()
        },
        modifier = modifier
            .height(46.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = disabledAlpha),
            contentColor = Color.White,
            disabledContentColor =  Color.White.copy(alpha = disabledAlpha)
        ),
        enabled = enabled,
        shape = CircleShape
    ) {
        AnimatedContent(targetState = isLoading) { state ->
            if (state) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .size(22.dp),
                    color = LocalContentColor.current,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

    }
}
