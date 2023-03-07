package com.example.tinn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.tinn.ui.theme.TinnTheme
import com.example.tinn.ui.navigation.AppNavHost
import com.example.tinn.ui.navigation.Screens
import com.example.tinn.utils.*
import com.example.tinn.viewModel.ErrorObserver
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinnTheme {
                val stateUI = rememberSystemUiController()
                stateUI.setStatusBarColor(MaterialTheme.colors.background)

                val statusAuthorization = LocalContext.current.getSharedPreferences(
                    AUTHORIZATION, MODE_PRIVATE
                ).getString(STATUS_AUTHORIZATION, "")

                val startDestination = getStartDestination(statusAuthorization!!)

                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                ObserverErrorMessage(snackbarHostState)

                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    content = { padding ->
                        AppNavHost(
                            navController = navController,
                            startDestination = startDestination,
                            modifier = Modifier.padding(padding)
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ObserverErrorMessage(snackBarState: SnackbarHostState) {
    val message by ErrorObserver.errorMessage.observeAsState()

    LaunchedEffect(key1 = message, block = {
        message?.let {
            val message = it.getMessage()
            if (message.isNotEmpty()) snackBarState.showSnackbar(message)
        }
    })
}

private inline fun getStartDestination(statusAuthorization: String): String {
    return when (statusAuthorization) {
        IS_AUTHORIZATION -> Screens.Main.route
        NOT_AUTHORIZATION -> Screens.SignIn.route
        VERIFICATION_EMAIL -> Screens.ConfirmEmail.route
        else -> Screens.InputInfoUser.route
    }
}
