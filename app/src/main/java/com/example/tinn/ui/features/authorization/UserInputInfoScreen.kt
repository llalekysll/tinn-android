package com.example.tinn.ui.features.authorization

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tinn.ui.components.AppButton
import com.example.tinn.ui.components.Spinner
import com.example.tinn.ui.components.TextFieldsWithLabelError
import com.example.tinn.ui.theme.Blue
import com.example.tinn.ui.theme.Gray
import com.example.tinn.utils.AUTHORIZATION
import com.example.tinn.utils.DigitVisualTransformation
import com.example.tinn.utils.TOKEN
import com.example.tinn.viewModel.UserViewModel

@Composable
fun UserInputInfoScreen(navController: NavController) {
    val token = LocalContext.current.getSharedPreferences(
        AUTHORIZATION, ComponentActivity.MODE_PRIVATE
    ).getString(TOKEN, "")

    val viewModel: UserViewModel = viewModel()

    var login by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Продолжите регистрацию",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        TextFieldsWithLabelError(
            value = login,
            onValueChange = { login = it },
            labelText = "Логин"
        )

        TextFieldsWithLabelError(
            value = firstName,
            onValueChange = { firstName = it },
            labelText = "Имя",
        )

        TextFieldsWithLabelError(
            value = secondName,
            onValueChange = { secondName = it },
            labelText = "Фамилия",
        )

        Spinner(
            items = listOf("Мужской", "Женский"),
            hint = if (sex == "") "Выберите пол" else sex,
            borderColor = Gray,
            onClick = { sex = it }
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextFieldsWithLabelError(
                value = phone,
                modifier = Modifier.width(200.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                onValueChange = { if (phone.length < 10 && it.isDigitsOnly()) phone = it },
                visualTransformation = DigitVisualTransformation("+7-000-000-00-00", '0'),
                labelText = "Телефон",
            )

            TextFieldsWithLabelError(
                value = dateOfBirth,
                onValueChange = {
                    if (dateOfBirth.length < 8 && it.isDigitsOnly()) {
                        dateOfBirth = it
                    }
                },
                modifier = Modifier.width(174.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                visualTransformation = DigitVisualTransformation("00/00/0000", '0'),
                labelText = "ДД/ММ/ГГГГ",
            )
        }

        val annotatedString = buildAnnotatedString {
            append("Нажимая на кнопку 'Регистрация' вы даете согласие на ")
            pushStringAnnotation("обработку персональных данных", "https://tinn.io/main")
            withStyle(SpanStyle(color = Blue)) {
                append("обработку персональных данных")
            }
        }

        ClickableText(
            text = annotatedString,
            modifier = Modifier.fillMaxWidth(),
            onClick = { offset ->
                annotatedString.getStringAnnotations(
                    "обработку персональных данных",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {

                }
            }
        )

        AppButton(
            onClick = {
                viewModel.putUserInfo(
                    login,
                    firstName,
                    secondName,
                    sex,
                    phone,
                    dateOfBirth,
                    token
                )
            },
            modifier = Modifier.padding(top = 16.dp),
            enabled = login.isNotEmpty()
                    && firstName.isNotEmpty()
                    && secondName.isNotEmpty()
                    && sex.isNotEmpty()
                    && phone.length == 10
                    && dateOfBirth.length == 8,
            text = "Регистрация"
        )
    }
}

