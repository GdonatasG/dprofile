package com.donatas.dprofile.features.aboutme.experience.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.donatas.dprofile.features.aboutme.experience.presentation.ExperienceViewModel

@Composable
fun ExperienceView(
    model: ExperienceViewModel,
    lazyListState: LazyListState? = null
) {
    val listState: LazyListState = lazyListState ?: rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(100) {
            Text(text = "ExperienceView")
        }
    }
}
