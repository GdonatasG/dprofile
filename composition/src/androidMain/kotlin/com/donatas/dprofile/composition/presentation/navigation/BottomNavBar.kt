package com.donatas.dprofile.composition.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.donatas.dprofile.compose.theme.surfaceDarkColor
import com.donatas.dprofile.composition.R

enum class Tab(val route: String, val title: String, val image: @Composable () -> ImageVector) {
    ABOUT_ME(route = "about_me", title = "About Me", image = { Icons.Outlined.Info }), GITHUB(
        route = "github",
        title = "Github",
        image = { ImageVector.vectorResource(id = R.drawable.github) }),
    CONTACTS(route = "contacts", title = "Contacts", image = { Icons.Outlined.AccountBox }),
}

@Composable
fun BottomNavBar(navController: NavController, tabs: List<Tab>) {
    NavigationBar(
        modifier = Modifier.shadow(8.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        val current by navController.currentBottomNavScreenAsState()
        tabs.forEach { destination ->
            val selected: Boolean = current.route == destination.route

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = destination.image(), contentDescription = destination.title
                    )
                },
                selected = selected,
                alwaysShowLabel = true,
                label = {
                    Text(
                        text = destination.title, maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                },
                onClick = Click@{
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                        restoreState = true
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
private fun NavController.currentBottomNavScreenAsState(): State<Tab> {
    val selectedItem = remember { mutableStateOf<Tab>(Tab.ABOUT_ME) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == Tab.ABOUT_ME.route } -> {
                    selectedItem.value = Tab.ABOUT_ME
                }

                destination.hierarchy.any { it.route == Tab.GITHUB.route } -> {
                    selectedItem.value = Tab.GITHUB
                }

                destination.hierarchy.any { it.route == Tab.CONTACTS.route } -> {
                    selectedItem.value = Tab.CONTACTS
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


