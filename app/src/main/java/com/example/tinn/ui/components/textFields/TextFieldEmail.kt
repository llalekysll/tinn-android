package com.example.tinn.ui.components.textFields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.tinn.utils.emailIfValid

@Composable
fun TextFieldEmail(email: String, onValueChange: (newValue: String) -> Unit) {
    TextFieldsWithLabelError(
        value = email,
        onValueChange = { text -> onValueChange(text) },
        labelText = "Введите email",
        isError = email.emailIfValid().not(),
        errorText = "Email не валиден",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun TextFieldEmailWithButtonChange(
    email: String,
    onSave: () -> Unit,
    onValueChange: (newValue: String) -> Unit
) {
    TextFieldsWithButtonChange(
        value = email,
        onValueChange = { text -> onValueChange(text) },
        caption = "Электронная почта",
        onSave = { onSave() },
        isError = email.emailIfValid().not(),
        errorText = "Email не валиден",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}