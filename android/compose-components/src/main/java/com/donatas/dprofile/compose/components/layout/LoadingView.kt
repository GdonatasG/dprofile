package com.donatas.dprofile.compose.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.DCircularProgressIndicator
import com.donatas.dprofile.compose.components.state.getImeWithNavigationBarsPadding

@Composable
fun LoadingView(
    paddingValues: PaddingValues = PaddingValues(
        top = 16.dp, start = 16.dp, end = 16.dp, bottom = getImeWithNavigationBarsPadding().value
    )
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DCircularProgressIndicator()
    }
}
