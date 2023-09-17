package com.donatas.dprofile.composition.presentation.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.donatas.dprofile.composition.R
import com.donatas.dprofile.composition.presentation.BottomTab

internal val BottomTab.Type.route: String
    get() = when (this) {
        BottomTab.Type.ABOUT_ME -> "about_me"
        BottomTab.Type.GITHUB -> "github"
        BottomTab.Type.CONTACTS -> "contacts"
    }

internal val BottomTab.Type.title: String
    get() = when (this) {
        BottomTab.Type.ABOUT_ME -> "About Me"
        BottomTab.Type.GITHUB -> "Github"
        BottomTab.Type.CONTACTS -> "Contacts"
    }

internal val BottomTab.Type.image: @Composable () -> ImageVector
    get() = when (this) {
        BottomTab.Type.ABOUT_ME -> {
            { ImageVector.vectorResource(id = R.drawable.info) }
        }

        BottomTab.Type.GITHUB -> {
            { ImageVector.vectorResource(id = R.drawable.github) }
        }

        BottomTab.Type.CONTACTS -> {
            { ImageVector.vectorResource(id = R.drawable.contacts) }
        }
    }

@Composable
fun BottomNavBar(navController: NavController, tabs: List<BottomTab>, onSelect: (BottomTab) -> Unit) {
    val current by navController.currentBottomNavScreenAsState()

    LaunchedEffect(current) {
        tabs.firstOrNull { it.type == current }?.let {
            onSelect(it)
        }
    }

    NavigationBar(
        // modifier = Modifier.shadow(8.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        tabs.forEach { tab ->
            val selected: Boolean = current.route == tab.type.route

            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = tab.type.image(), contentDescription = tab.type.title
                    )
                },
                selected = selected,
                alwaysShowLabel = true,
                label = {
                    Text(
                        text = tab.type.title, maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                },
                onClick = Click@{
                    navController.navigate(tab.type.route) {
                        launchSingleTop = true
                        restoreState = false
                        popUpTo(navController.graph.findStartDestination().route!!) {
                            saveState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.background,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Stable
@Composable
private fun NavController.currentBottomNavScreenAsState(): State<BottomTab.Type> {
    val selectedItem = remember { mutableStateOf<BottomTab.Type>(BottomTab.Type.ABOUT_ME) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == BottomTab.Type.ABOUT_ME.route } -> {
                    selectedItem.value = BottomTab.Type.ABOUT_ME
                }

                destination.hierarchy.any { it.route == BottomTab.Type.GITHUB.route } -> {
                    selectedItem.value = BottomTab.Type.GITHUB
                }

                destination.hierarchy.any { it.route == BottomTab.Type.CONTACTS.route } -> {
                    selectedItem.value = BottomTab.Type.CONTACTS
                }
            }
        }

        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}


