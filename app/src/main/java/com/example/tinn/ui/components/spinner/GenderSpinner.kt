package com.example.tinn.ui.components.spinner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinn.R
import com.example.tinn.data.modelForJSON.Gender
import com.example.tinn.ui.components.HorizontalSpacer
import com.example.tinn.ui.theme.Alpha

@Composable
fun GenderSpinner(
    items: List<Gender>,
    hint: String,
    hintColor: Color = Color.Gray,
    textColor: Color = Color.Black,
    borderColor: Color = Alpha,
    fontSize: TextUnit = 18.sp,
    modifier: Modifier = Modifier.padding(top = 16.dp),
    onClick: (item: Gender) -> Unit
) {

    Spinner(
        hint = hint,
        hintColor = hintColor,
        textColor = textColor,
        fontSize = fontSize,
        modifier = modifier
    ) { hide ->
        for (item in items) {
            DropdownMenuItem(onClick = {
                hide()
                onClick(item)
            }) {
                Text(text = item.title, color = hintColor)
            }
        }
    }

    HorizontalSpacer(colorSpacer = borderColor)
}