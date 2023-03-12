package com.example.tinn.ui.features.main.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tinn.ui.components.textFields.TextFieldEmailWithButtonChange
import com.example.tinn.ui.components.textFields.TextFieldPhoneNumberWithButtonChange
import com.example.tinn.ui.components.textFields.TextFieldsWithButtonChange
import com.example.tinn.ui.components.textFields.TextFieldsWithLabelError
import com.example.tinn.ui.theme.Blue
import com.example.tinn.viewModel.UserViewModel
import java.util.*

@Composable
fun CommonSettingsView() {
    val viewModel: UserViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var email by remember { mutableStateOf("skat@gmail.com") }

        var phone by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        var language by remember { mutableStateOf("") }

        TextFieldEmailWithButtonChange(
            email = email,
            onValueChange = { email = it },
            onSave = { }
        )

        TextFieldPhoneNumberWithButtonChange(
            value = phone,
            onSave = {  },
            onValueChange = { phone = it }
        )

        TextFieldsWithButtonChange(
            value = address,
            onSave = {  },
            onValueChange = { address = it },
            labelText = "Адрес профиля"
        )
    }
}


