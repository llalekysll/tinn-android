package com.example.tinn.ui.features.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.tinn.ui.navigation.MainBottomNavigation
import com.example.tinn.ui.navigation.MainNavHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { MainBottomNavigation(navController = navController)}
    ) { paddingValues ->
        MainNavHost(mainNavController = navController, modifier = Modifier.padding(paddingValues))
    }
}