package com.donatas.dprofile.compose.components.state

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getImeWithNavigationBarsPadding(initialPadding: Dp = 16.dp): State<Dp> {
    val imeBottom = WindowInsets.ime.asPaddingValues()
    val navBar = WindowInsets.navigationBars.asPaddingValues()
    val captionBar = WindowInsets.captionBar.asPaddingValues()

    return remember(imeBottom, navBar, captionBar) {
        derivedStateOf {
            maxOf(
                initialPadding + navBar.calculateBottomPadding() + captionBar.calculateBottomPadding(),
                imeBottom.calculateBottomPadding() + 8.dp + captionBar.calculateBottomPadding()
            )
        }
    }
}

@Composable
fun getImePadding(initialPadding: Dp = 16.dp): State<Dp> {
    val imeBottom = WindowInsets.ime.asPaddingValues()
    val captionBar = WindowInsets.captionBar.asPaddingValues()

    return remember(imeBottom, captionBar) {
        derivedStateOf {
            maxOf(
                initialPadding + captionBar.calculateBottomPadding(),
                imeBottom.calculateBottomPadding() + 8.dp + captionBar.calculateBottomPadding()
            )
        }
    }
}
