package com.example.tinn.ui.components.textFields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.tinn.utils.DigitVisualTransformation

@Composable
fun TextFieldPhoneNumber(
    value: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp),
    onValueChange: (newValue: String) -> Unit
) {
    TextFieldsWithLabelError(
        value = value,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        onValueChange = {
            if ((value.length < 10 && it.isDigitsOnly()) || value.length >= it.length) {
                onValueChange(it)
            }
        },
        visualTransformation = DigitVisualTransformation("+7-000-000-00-00", '0'),
        labelText = "Телефон",
    )
}

@Composable
fun TextFieldPhoneNumberWithButtonChange(
    value: String,
    onSave: () -> Unit,
    onValueChange: (newValue: String) -> Unit
) {
    TextFieldsWithButtonChange(
        value = value,
        onSave = { onSave() },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        onValueChange = {
            if (value.length < 10 && it.isDigitsOnly() || value.length > it.length) {
                onValueChange(it)
            }
        },
        visualTransformation = DigitVisualTransformation("+7-000-000-00-00", '0'),
        caption = "Телефон",
    )
}