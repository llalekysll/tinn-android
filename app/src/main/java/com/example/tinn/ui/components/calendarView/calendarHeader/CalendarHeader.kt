package com.example.tinn.ui.components.calendarView.calendarHeader

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tinn.ui.components.calendarView.CalendarHeaderMonth
import com.example.tinn.ui.theme.CalendarHeaderTheme
import com.example.tinn.utils.getYear
import com.example.tinn.utils.parseDateToShortString
import java.util.*

@Composable
fun CalendarHeader(
    currentMode: String,
    changeMode: (mode: String) -> Unit,
    currentDate: MutableState<Calendar>,
    theme: CalendarHeaderTheme
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(theme.headerBackgroundColor)
            .padding(top = 16.dp)
    ) {
        val colorYear = if (currentMode == "year")
            theme.headerSelectedModeColor else theme.headerUnSelectedModeColor
        Text(
            text = currentDate.value.getYear().toString(),
            style = MaterialTheme.typography.h5,
            color = colorYear,
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable { changeMode("year") }
        )

        val colorMonth = if (currentMode == "month")
            theme.headerSelectedModeColor else theme.headerUnSelectedModeColor
        Text(
            text = currentDate.value.parseDateToShortString()!!,
            style = MaterialTheme.typography.h4,
            color = colorMonth,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp)
                .clickable { changeMode("month") }
        )

        if (currentMode == "month") {
            CalendarHeaderMonth(currentDate = currentDate, theme = theme)
        }
    }
}
