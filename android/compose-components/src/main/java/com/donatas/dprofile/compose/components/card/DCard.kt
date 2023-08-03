package com.donatas.dprofile.compose.components.card

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun DCard(
    shape: Shape = MaterialTheme.shapes.extraSmall,
    content: @Composable () -> Unit
) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.05f)
        )
    ) {
        content()
    }
}
