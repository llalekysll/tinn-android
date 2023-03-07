package com.example.tinn.ui.features.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tinn.ui.components.TextFieldsWithLabelError
import com.example.tinn.utils.PhoneNumberVisualTranformation

@Composable
fun UserInputInfoScreen(navController: NavController) {
    var login by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf(0) }
    var phone by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {

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

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextFieldsWithLabelError(
                value = phone,
                modifier = Modifier.width(221.dp),
                onValueChange = { if (phone.length < 10) phone = it },
                visualTransformation = PhoneNumberVisualTranformation("+7-000-000-00-00", '0'),
                labelText = "Телефон",
            )

            TextFieldsWithLabelError(
                value = dateOfBirth,
                onValueChange = { dateOfBirth = it },
                modifier = Modifier.width(174.dp),
                labelText = "ДД/ММ/ГГГГ",
            )
        }

    }
}