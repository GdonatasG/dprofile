package com.donatas.dprofile.features.aboutme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.aboutme.presentation.AboutMeViewModel
import com.donatas.dprofile.features.aboutme.ui.AboutMeView
import org.koin.androidx.compose.getViewModel

actual class AboutMeScreen actual constructor() : Screen {
    @Composable
    override fun Compose() {
        val viewModel: AboutMeViewModel = getViewModel<AboutMeViewModel>()

        AppScaffold(
            tabBar = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ElevatedButton(
                        onClick = {

                        }
                    ) {
                        Text(text = "Exp")
                    }
                    ElevatedButton(
                        onClick = {

                        }
                    ) {
                        Text(text = "Edu")
                    }
                    ElevatedButton(
                        onClick = {

                        }
                    ) {
                        Text(text = "Skills")
                    }
                    ElevatedButton(
                        onClick = {

                        }
                    ) {
                        Text(text = "Road")
                    }
                }
            }
        ) {
            AboutMeView(viewModel)
        }
    }
}
