package com.example.tinn.ui.features.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tinn.R
import com.example.tinn.ui.components.AppButton
import com.example.tinn.ui.components.TextFieldsWithLabelError
import com.example.tinn.ui.navigation.Screens
import com.example.tinn.viewModel.AuthorizationViewModel

@Composable
fun VerificationEmailScreen(navController: NavController) {
    val viewModel = viewModel(AuthorizationViewModel::class.java)
    val emailIsVerificated by viewModel.emailIsVerificated.observeAsState(false)

    LaunchedEffect(key1 = Unit, block = {
        viewModel.checkEmailIsVerificated()
    })

    if (emailIsVerificated == true) {
        navController.navigate(Screens.Main.route) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.ic_email),
            contentDescription = "email"
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Подтердите email для \n защиты вашего профиля",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 32.dp),
            text = "На Вашу почту было отправлено письмо с кодом подтверждения.Введите код ниже, чтобы подтвердить электронную почту",
            textAlign = TextAlign.Center
        )

        var code by remember { mutableStateOf("") }
        TextFieldsWithLabelError(
            value = code,
            labelText = "Код",
            onValueChange = { code = it },
            isError = code.isDigitsOnly().not() && code.isNotEmpty(),
            errorText = "Код состоит только из цифр"
        )

        AppButton(
            modifier = Modifier.padding(top = 16.dp),
            onClick = { viewModel.verificationEmail(code) },
            enabled = code.isDigitsOnly() && code.isNotEmpty(),
            text = "Подтвердить"
        )
    }
}