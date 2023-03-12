package com.example.tinn.ui.features.main

import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinn.ui.components.Toolbar
import com.example.tinn.ui.navigation.MainBottomNavigation
import com.example.tinn.ui.navigation.MainNavHost
import com.example.tinn.ui.navigation.MainScreens
import com.example.tinn.ui.navigation.Screens
import com.example.tinn.utils.AUTHORIZATION

@Composable
fun MainScreen(mainNavController: NavController) {
    val navController = rememberNavController()
    val pref = LocalContext.current.getSharedPreferences(AUTHORIZATION, MODE_PRIVATE)

    Scaffold(
        topBar = { Toolbar(
            exit = {
                pref.edit().clear().apply()
                mainNavController.navigate(Screens.SignIn.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            openSettings = {navController.navigate(MainScreens.Settings.route)},
            openProfile = {navController.navigate(MainScreens.Profile.route)}

        ) },
        bottomBar = { MainBottomNavigation(navController = navController) }
    ) { paddingValues ->
        MainNavHost(mainNavController = navController, modifier = Modifier.padding(paddingValues))
    }
}