package com.donatas.dprofile.features.github.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.appbar.DAppBar
import com.donatas.dprofile.compose.components.button.ActionButton
import com.donatas.dprofile.compose.components.button.BackActionButton
import com.donatas.dprofile.compose.components.layout.AppScaffold
import com.donatas.dprofile.compose.components.textfield.DSearchField
import com.donatas.dprofile.feature.Screen
import com.donatas.dprofile.features.github.search.presentation.GithubSearchViewModel
import com.donatas.dprofile.features.github.search.ui.GithubSearchView
import org.koin.androidx.compose.getViewModel

actual class GithubSearchScreen actual constructor() : Screen {

    companion object {
        const val route: String = "github_search_screen"
    }

    @Composable
    override fun Compose() {
        val viewModel: GithubSearchViewModel = getViewModel<GithubSearchViewModel>()
        AppScaffold(
            appBar = {
                DAppBar(
                    navigationIcon = {
                        BackActionButton(
                            onClick = viewModel::onBack
                        )
                    },
                    title = {
                        DSearchField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(42.dp),
                            value = "",
                            onValueChange = {},
                            placeHolder = "Search repositories",
                            contentPadding = PaddingValues(0.dp),
                            onClear = {}
                        )
                    },
                    centerTitle = false,
                    actions = {
                        ActionButton(onClick = viewModel::onFilter) {
                            Icon(imageVector = Icons.Outlined.FilterAlt, contentDescription = "Filter repositories")
                        }
                    }
                )
            }
        ) {
            GithubSearchView(viewModel)
        }
    }
}
