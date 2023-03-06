package com.example.tinn.ui.navigation

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tinn.ui.theme.Blue

@Composable
fun MainBottomNavigation(navController: NavController) {
    val state = navController.currentBackStackEntryAsState()
    val destination = state.value?.destination

    destination?.let {
        BottomNavigation(
            backgroundColor = Color.White,
            elevation = 16.dp
        ) {
            LazyRow() {
                items(mainScreensList) { currentScreen ->
                    val isSelected = destination.hierarchy.any { it.route == currentScreen.route }
                    val color = if (isSelected) Blue else Color.Black

                    BottomNavigationItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(currentScreen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = currentScreen.idIcon),
                                contentDescription = currentScreen.label,
                                tint = color
                            )
                        },
                        label = { Text(text = currentScreen.label, color = color) },
                    )
                }
            }
        }
    }
}