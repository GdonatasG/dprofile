package com.donatas.dprofile.composition.navigation.screens.components.filters

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.ModalDivider
import com.donatas.dprofile.compose.components.appbar.AppBarTitle
import com.donatas.dprofile.compose.components.button.FullWidthButton
import com.donatas.dprofile.compose.components.layout.ModalScaffold
import com.donatas.dprofile.compose.components.text.SectionTitle
import com.donatas.dprofile.composition.navigation.screens.FiltersModalFactory
import com.donatas.dprofile.features.filter.FiltersViewModel

class DefaultFiltersModalFactory : FiltersModalFactory {

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun Compose(viewModel: FiltersViewModel) {
        val filters by viewModel.filterStore.collectAsState()

        BackHandler {
            viewModel.onBack()
        }

        ModalScaffold {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                AppBarTitle(title = "Filters")
                TextButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = viewModel::onResetFilters
                ) {
                    Text(text = "Reset")
                }
            }
            ModalDivider()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(filters.data) { filterIndex, filter ->
                    SectionTitle(
                        title = filter.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        filter.list.forEach { filterValue ->
                            val state by filterValue.state.collectAsState()
                            Row(
                                modifier = Modifier.clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {
                                        filter.onToggle(filterValue)
                                    }
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                RadioButton(selected = state.selected, onClick = null)
                                Text(
                                    text = state.name,
                                    style = MaterialTheme.typography.bodySmall,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                    if (filterIndex < filters.data.size - 1) {
                        Spacer(modifier = Modifier.height(10.dp))
                        ModalDivider()
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            FullWidthButton(
                modifier = Modifier
                    .padding(16.dp),
                title = "Apply",
                onClick = viewModel::onApply
            )
        }
    }
}
