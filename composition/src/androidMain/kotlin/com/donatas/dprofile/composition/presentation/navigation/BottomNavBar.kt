package com.donatas.dprofile.composition.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.donatas.dprofile.compose.components.ModalDivider
import com.donatas.dprofile.compose.components.extension.gradientShimmerEffect
import com.donatas.dprofile.compose.components.extension.loadingShimmerEffect
import com.donatas.dprofile.composition.AppTutorial
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
fun BottomNavBar(
    navController: NavController,
    tabs: List<BottomTab>,
    onSelect: (BottomTab) -> Unit,
    appTutorial: AppTutorial
) {
    val current by navController.currentBottomNavScreenAsState()
    val tutorialState by appTutorial.state.collectAsState()

    if (tutorialState.isFinished) {
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
                        if (current.route == tab.type.route) return@Click

                        onSelect(tab)
                        navController.navigate(tab.type.route) {
                            launchSingleTop = false
                            restoreState = true
                           /* popUpTo(navController.graph.findStartDestination().route!!) {
                                saveState = true
                            }*/
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
    } else {
        Column {
            ModalDivider()
            Row(
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .then(if (tutorialState.step == 1 && !tutorialState.isStarted) Modifier.gradientShimmerEffect() else Modifier)
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                if (tutorialState.step > 1) {
                    IconButton(onClick = appTutorial::previous) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Previous step"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                if (tutorialState.isLastStep) {
                    IconButton(onClick = appTutorial::finish) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Finish tutorial"
                        )
                    }
                } else {
                    IconButton(onClick = appTutorial::next) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Next step"
                        )
                    }
                }
            }
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


