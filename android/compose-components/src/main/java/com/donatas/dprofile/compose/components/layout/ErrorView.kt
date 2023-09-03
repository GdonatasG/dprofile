package com.donatas.dprofile.compose.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.donatas.dprofile.compose.components.R
import com.donatas.dprofile.compose.components.button.FullWidthButton
import com.donatas.dprofile.compose.components.state.getImeWithNavigationBarsPadding
import com.donatas.dprofile.compose.theme.getSecondaryTextColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ErrorView(
    title: String,
    message: String? = null,
    paddingValues: PaddingValues = PaddingValues(
        top = 16.dp, start = 16.dp, end = 16.dp, bottom = getImeWithNavigationBarsPadding().value
    ),
    onRetry: () -> Unit
) {
    val keyboardVisible = WindowInsets.isImeVisible

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!keyboardVisible) {
                Animation()
            }

            Text(
                text = title, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center
            )
            message?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium.copy(color = getSecondaryTextColor()),
                    textAlign = TextAlign.Center
                )
            }
        }

        FullWidthButton(title = "Retry", onClick = onRetry)
    }

}

@Composable
private fun Animation() {
    val animationComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error_animation))
    val animationProgress by animateLottieCompositionAsState(
        composition = animationComposition, iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        modifier = Modifier.size(210.dp),
        composition = animationComposition, progress = { animationProgress }
    )
}
