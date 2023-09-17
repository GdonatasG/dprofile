package com.donatas.dprofile.compose.components.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DLazyTabRow(
    selectedIndex: Int,
    items: List<String>,
    contentPadding: PaddingValues = PaddingValues(),
    onTabClick: (index: Int) -> Unit
) {
    val scope = rememberCoroutineScope()

    val listState = rememberLazyListState()

    var selected by remember { mutableIntStateOf(selectedIndex) }

    LaunchedEffect(selectedIndex) {
/*        if (selectedIndex == selected) return@LaunchedEffect*/

        val selectedItemInfo = listState.layoutInfo.visibleItemsInfo.find { it.index == selectedIndex }
        val isItemSelectedFullyVisible = selectedItemInfo?.run {
            // Calculate the rightmost and leftmost positions of the selectedIndex item.
            val rightEdge = this.offset + this.size
            val leftEdge = this.offset
            // Check if the rightmost and leftmost positions are within the bounds of the LazyRow.
            rightEdge <= listState.layoutInfo.viewportEndOffset &&
                    leftEdge >= listState.layoutInfo.viewportStartOffset
        } ?: false

        selected = selectedIndex
        if (!isItemSelectedFullyVisible) {
            listState.animateScrollToItem(selectedIndex)
        }

    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
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
