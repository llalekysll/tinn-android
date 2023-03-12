package com.example.tinn.ui.components.calendarView

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tinn.R
import com.example.tinn.ui.theme.CalendarHeaderTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CalendarHeaderMonth(
    currentDate: MutableState<Calendar>, theme: CalendarHeaderTheme
) {
    val header = SimpleDateFormat("LLLL, yyyy", Locale("ru")).format(currentDate.value.time)

    theme.apply {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(headerMonthBackgroundColor)
                .padding(bottom = 16.dp)
        ) {
            ButtonForChangeCurrentMonth(
                currentDate = currentDate,
                iconId = R.drawable.baseline_arrow_left,
                color = headerTextColor,
                index = -1
            )

            Text(
                text = header.uppercase(),
                style = headerTextStyle,
                fontSize = headerTextSize,
                fontWeight = headerTextWeight,
                color = headerTextColor,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            ButtonForChangeCurrentMonth(
                currentDate = currentDate,
                iconId = R.drawable.baseline_arrow_right,
                color = headerTextColor,
                index = 1
            )
        }
    }
}

@Composable
private fun ButtonForChangeCurrentMonth(
    currentDate: MutableState<Calendar>, iconId: Int, color: Color, index: Int
) {
    Icon(painter = painterResource(id = iconId),
        contentDescription = "change month",
        tint = color,
        modifier = Modifier
            .size(70.dp)
            .clickable {
                val tempDate = currentDate.value.clone() as Calendar
                tempDate.add(Calendar.MONTH, index)
                currentDate.value = tempDate
            })
}