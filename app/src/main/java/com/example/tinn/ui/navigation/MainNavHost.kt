package com.example.tinn.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tinn.ui.features.main.profile.ProfileScreen
import com.example.tinn.ui.features.main.settings.MainSettingsScreen

@Composable
fun MainNavHost(mainNavController: NavHostController, modifier: Modifier) {
    NavHost(
        modifier = modifier,
        navController = mainNavController,
        startDestination = MainScreens.Home.route
    ) {
        composable(MainScreens.Home.route) {
        }

        composable(MainScreens.Application.route) {

        }

        composable(MainScreens.Forum.route) {

        }

        composable(MainScreens.Popular.route) {

        }

        composable(MainScreens.Room.route) {

        }

        composable(MainScreens.Marketplace.route) {

        }

        composable(MainScreens.Profile.route) {
            ProfileScreen(mainNavController)
        }

        composable(MainScreens.Settings.route) {
            MainSettingsScreen()
        }
    }
}