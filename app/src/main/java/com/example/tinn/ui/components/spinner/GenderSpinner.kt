package com.example.tinn.ui.components

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
import com.example.tinn.ui.theme.Alpha

@Composable
fun Spinner(
    items: List<Gender>,
    hint: String,
    hintColor: Color = Color.Gray,
    textColor: Color = Color.Black,
    borderColor: Color = Alpha,
    fontSize: TextUnit = 18.sp,
    modifier: Modifier = Modifier.padding(top = 16.dp),
    onClick: (item: Gender) -> Unit
) {
    var isExpand by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable { isExpand = true }
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = hint,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                color = hintColor,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                modifier = Modifier.size(32.dp),
                contentDescription = "Раскрыть пол",
                tint = textColor
            )
        }

        DropdownMenu(
            expanded = isExpand,
            onDismissRequest = { isExpand = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            for (item in items) {
                DropdownMenuItem(onClick = {
                    isExpand = false
                    onClick(item)
                }) {
                    Text(text = item.title, color = hintColor)
                }
            }
        }

        HorizontalSpacer(colorSpacer = borderColor)
    }
}