package com.example.calendar.calendarView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.calendar.util.getDate
import com.example.calendar.util.getDayOfWeek
import com.example.calendar.util.getMonth
import java.util.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarView(
    calendarTheme: CalendarTheme = CalendarTheme(),
    selectedDate: Calendar?,
    changeSelectedDate: (calendar: Calendar) -> Unit
) {
    var currentDate = remember { mutableStateOf(Calendar.getInstance()) }
    var days by remember { mutableStateOf(mutableListOf<Calendar>()) }
    val weeks = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(calendarTheme.backgroundColor)
    ) {
        CalendarHeader(
            currentDate = currentDate,
            theme = calendarTheme.calendarHeaderTheme
        )

        days = getDays(currentDate.value)
        LazyVerticalGrid(columns = GridCells.Fixed(7), content = {
            items(weeks) { weekDay ->
                Text(
                    weekDay,
                    color = calendarTheme.calendarHeaderTheme.headerTextColor,
                    textAlign = TextAlign.Center
                )
            }
            items(days) { day ->
                val date = selectedDate?.getDate() ?: 0
                val month = selectedDate?.getMonth() ?: 0
                val isSelected = date == day.getDate() && month == day.getMonth()

                CalendarItem(
                    day = day.getDate().toString(),
                    isSelected = isSelected,
                    calendarItemTheme = calendarTheme.calendarItemTheme
                ) {
                    changeSelectedDate(day)
                }
            }
        })
    }
}
private fun getDays(currentDate: Calendar): MutableList<Calendar> {

    val tempDate = currentDate.clone() as Calendar
    tempDate.set(Calendar.DATE, 1)

    var countDayBeforeCurrentMonth = tempDate.getDayOfWeek() - 1
    if (countDayBeforeCurrentMonth == 0) countDayBeforeCurrentMonth = 7
    tempDate.add(Calendar.DATE, 1 - countDayBeforeCurrentMonth)

    val tempDays = mutableListOf<Calendar>()
    val countDayInMonth =
        currentDate.getActualMaximum(Calendar.DAY_OF_MONTH) + countDayBeforeCurrentMonth - 1
    repeat(countDayInMonth) {
        tempDays.add(tempDate.clone() as Calendar)
        tempDate.add(Calendar.DATE, 1)
    }
    return tempDays
}