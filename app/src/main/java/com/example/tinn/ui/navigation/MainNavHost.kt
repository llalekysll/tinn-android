package com.example.tinn.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavHost(mainNavController: NavHostController) {
    NavHost(navController = mainNavController, startDestination = MainScreens.Home.route) {
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
    }
}