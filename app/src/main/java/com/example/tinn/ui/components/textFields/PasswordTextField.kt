package com.example.tinn.ui.components.textFields

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.tinn.R
import com.example.tinn.ui.theme.Blue
import com.example.tinn.ui.theme.DarkGray1

@Composable
fun TextFieldPassword(
    password: String,
    onValueChange: (newValue: String) -> Unit,
    labelText: String,
    isError: Boolean,
    errorText: String
) {
    var isShowPassword by remember { mutableStateOf(false) }

    TextFieldsWithLabelError(
        value = password,
        onValueChange = { text -> onValueChange(text) },
        rightIcon = {
            IconButton(onClick = { isShowPassword = isShowPassword.not() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_eye),
                    contentDescription = "Показать пароль",
                    tint = if (!isShowPassword) DarkGray1 else Blue
                )
            }
        },
        labelText = labelText,
        errorText = errorText,
        isError = isError,
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
    )
}