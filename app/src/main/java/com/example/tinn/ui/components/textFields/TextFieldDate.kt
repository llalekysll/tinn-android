package com.example.tinn.ui.components.textFields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.tinn.R
import com.example.tinn.ui.components.HorizontalSpacer
import com.example.tinn.ui.theme.Gray
import com.example.tinn.utils.parseDateToNumberString
import java.util.*

@Composable
fun TextFieldDate(
    label: String,
    modifier: Modifier = Modifier,
    currentDate: Calendar?,
    colorLabel: Color = Gray,
    colorIcon: Color = Color.Black,
    colorText: Color = Color.Black,
    onClick: () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(text = label, color = colorLabel)

                currentDate?.let {
                    Text(text = currentDate.parseDateToNumberString()!!, color = colorText)
                }
            }

            IconButton(onClick = { onClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Выбрать дату",
                    tint = colorLabel
                )
            }
        }

        HorizontalSpacer()
    }
}