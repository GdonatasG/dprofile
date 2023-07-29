package com.donatas.dprofile.compose.components.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DLazyTabRow(
    selectedIndex: Int,
    items: List<String>,
    contentPadding: PaddingValues = PaddingValues(),
    onTabClick: (index: Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items) { index, label ->
            DTabItem(
                isSelected = selectedIndex == index, onClick = {
                    onTabClick(index)
                }, label = label
            )
        }
    }
}
