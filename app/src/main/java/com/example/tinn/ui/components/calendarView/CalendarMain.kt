package com.example.tinn.ui.components.calendarView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tinn.ui.components.calendarView.calendarContent.ChooseDayContent
import com.example.tinn.ui.components.calendarView.calendarContent.ChooseYearContent
import com.example.tinn.ui.components.calendarView.calendarHeader.CalendarHeader
import com.example.tinn.utils.getYear
import java.util.*


@Composable
fun CalendarView(
    calendarTheme: CalendarTheme = CalendarTheme(),
    selectedDate: Calendar?,
    changeSelectedDate: (calendar: Calendar) -> Unit
) {
    var currentDate = remember { mutableStateOf(Calendar.getInstance()) }
    var currentMode by remember { mutableStateOf("month") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(calendarTheme.backgroundColor)
    ) {
        CalendarHeader(
            currentMode = currentMode,
            changeMode = { currentMode = it },
            currentDate = currentDate,
            theme = calendarTheme.calendarHeaderTheme
        )

        if ("month" == currentMode) {
            ChooseDayContent(
                selectedDate = selectedDate,
                currentDate = currentDate.value,
                changeSelectedDate = { changeSelectedDate(it) },
                calendarTheme = calendarTheme
            )
        } else {
            ChooseYearContent(
                currentDate.value.getYear(),
                theme = calendarTheme.calendarItemYearTheme
            ) {
                val tempDate = currentDate.value.clone() as Calendar
                tempDate.set(Calendar.YEAR, it)
                currentDate.value = tempDate
            }
        }

    }
}