package com.example.tinn.ui.components.textFields

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tinn.ui.theme.Alpha

@Composable
fun TextFieldsWithLabelError(
    value: String,
    onValueChange: (newValue: String) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    rightIcon: @Composable () -> Unit = {},
    errorText: String = "",
    labelText: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 32.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    Column {
        TextField(
            value = value,
            onValueChange = { text -> onValueChange(text) },
            modifier = modifier,
            enabled = enabled,
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

