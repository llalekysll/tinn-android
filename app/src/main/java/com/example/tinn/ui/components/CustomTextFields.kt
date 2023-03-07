package com.example.tinn.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tinn.utils.emailIfValid
import com.example.tinn.R
import com.example.tinn.ui.theme.Alpha
import com.example.tinn.ui.theme.Blue
import com.example.tinn.ui.theme.DarkGray

@Composable
fun TextFieldsWithLabelError(
    value: String,
    onValueChange: (newValue: String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    rightIcon: @Composable () -> Unit = {},
    errorText: String = "",
    labelText: String = "",
    isError: Boolean = false,
    modifier: Modifier = Modifier.fillMaxWidth(),
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    Column {
        TextField(
            value = value,
            onValueChange = { text -> onValueChange(text) },
            modifier = modifier,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Alpha
            ),
            trailingIcon = { rightIcon() },
            label = { Text(labelText) },
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            isError = isError
        )

        if (isError) {
            Text(
                text = errorText,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(start = 40.dp)
            )
        }
    }
}

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
                    tint = if (!isShowPassword) DarkGray else Blue
                )
            }
        },
        labelText = labelText,
        errorText = errorText,
        isError = isError,
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
    )
}