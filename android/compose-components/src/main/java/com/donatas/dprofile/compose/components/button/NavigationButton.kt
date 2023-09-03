package com.donatas.dprofile.compose.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable

@Composable
fun BackActionButton(onClick: () -> Unit) {
    val colors = IconButtonDefaults.iconButtonColors(
      /*  contentColor = getSecondaryTextColor()*/
    )

    IconButton(
        onClick = onClick,
        colors = colors
    ) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back to previous screen")
    }

}
