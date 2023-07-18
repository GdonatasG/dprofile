package com.donatas.dprofile.features.template

import androidx.compose.runtime.Composable
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.template.presentation.TestViewModel
import com.donatas.dprofile.features.template.ui.TestView
import org.koin.androidx.compose.getViewModel

actual class TestScreen actual constructor() : Screen {
    @Composable
    override fun Compose() {
        val viewModel: TestViewModel = getViewModel<TestViewModel>()
        TestView(viewModel)
    }
}
