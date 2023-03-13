package com.example.tinn.ui.components.textFields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tinn.ui.components.TextWithCaption
import com.example.tinn.ui.theme.Alpha
import com.example.tinn.ui.theme.Blue
import com.example.tinn.ui.theme.Gray

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
                backgroundColor = Alpha,
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
fun TextFieldsWithButtonChange(
    value: String,
    onValueChange: (newValue: String) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onSave: () -> Unit,
    errorText: String = "",
    caption: String,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    var isEnabled by remember { mutableStateOf(false) }

    TextWithCaption(caption = caption) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                BasicTextField(
                    value = value,
                    onValueChange = { text -> onValueChange(text) },
                    textStyle = TextStyle(
                        color = if (isError) MaterialTheme.colors.error
                        else if (isEnabled) MaterialTheme.colors.onSurface
                        else MaterialTheme.colors.onBackground
                    ),
                    enabled = isEnabled,
                    keyboardOptions = keyboardOptions,
                    visualTransformation = visualTransformation,
                )

                if (isError) {
                    Text(
                        text = errorText,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.padding(start = 40.dp)
                    )
                }
            }

            Text(
                text = if (isEnabled) "cохранить" else "изменить",
                color = Blue,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable {
                        if (isEnabled) onSave()
                        isEnabled = isEnabled.not()
                    }
            )
        }
    }
}
