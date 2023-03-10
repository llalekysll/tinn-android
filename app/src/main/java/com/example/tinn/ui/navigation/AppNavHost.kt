package com.example.tinn.ui.navigation

import androidx.compose.runtime.Composable
 import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.tinn.ui.features.RegisterScreen
import com.example.tinn.ui.features.SignInScreen
import com.example.tinn.ui.features.authorization.VerificationEmailScreen
import com.example.tinn.ui.features.authorization.UserInputInfoScreen
import com.example.tinn.ui.features.main.MainScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screens.SignIn.route) {
            SignInScreen(navController)
        }

        composable(Screens.Registration.route) {
            RegisterScreen(navController)
        }

        composable(Screens.ConfirmEmail.route) {
            VerificationEmailScreen(navController)
        }

        composable(Screens.InputInfoUser.route) {
            UserInputInfoScreen(navController = navController)
        }

        composable(Screens.Main.route) {
            MainScreen(navController)
        }
    }
}

