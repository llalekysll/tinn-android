package com.example.tinn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.tinn.data.networkService.ServiceInterceptor
import com.example.tinn.data.networkService.ServiceInterceptor.token
import com.example.tinn.ui.theme.TinnTheme
import com.example.tinn.ui.navigation.AppNavHost
import com.example.tinn.ui.navigation.Screens
import com.example.tinn.utils.*
import com.example.tinn.viewModel.AuthorizationViewModel
import com.example.tinn.viewModel.ErrorObserver
import com.example.tinn.viewModel.UserViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var themeIsDark by remember { mutableStateOf(false) }

            TinnTheme(themeIsDark) {
                val viewModel: AuthorizationViewModel = viewModel()
                val userViewModel: UserViewModel = viewModel()

                val stateUI = rememberSystemUiController()
                stateUI.setStatusBarColor(MaterialTheme.colors.background)

                val pref = LocalContext.current.getSharedPreferences(AUTHORIZATION, MODE_PRIVATE)
                var startDestination by remember { mutableStateOf("") }

                var token by remember { mutableStateOf(pref.getString(TOKEN, "")) }
                LaunchedEffect(key1 = token, block = {
                    startDestination = if (!token.isNullOrEmpty()) {
                        ServiceInterceptor.token = token as String
                        viewModel.checkEmailIsVerificated()
                        ""
                    } else {
                        Screens.SignIn.route
                    }
                })

                val emailState by viewModel.emailIsVerificated.observeAsState(null)
                LaunchedEffect(key1 = emailState, block = {
                    emailState?.let {
                        startDestination = if (it || true) {
                            userViewModel.getUserInfo()
                            ""
                        } else Screens.ConfirmEmail.route
                    }
                })

                val userInfo by userViewModel.userInfo.observeAsState(null)
                LaunchedEffect(key1 = userInfo, block = {
                    userInfo?.let {
                        startDestination = if (it.userProfiles.login == null) {
                            Screens.InputInfoUser.route
                        } else {
                            Screens.Main.route
                        }
                    }
                })

                if (startDestination.isNotEmpty()) {
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
                            ) { themeIsDark = themeIsDark.not() }
                        }
                    )
                } else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
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
