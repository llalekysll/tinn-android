package com.example.tinn.ui.components.calendarView.calendarContent

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import com.example.tinn.ui.components.calendarView.CalendarItem
import com.example.tinn.ui.theme.CalendarTheme
import com.example.tinn.utils.getDate
import com.example.tinn.utils.getDayOfWeek
import com.example.tinn.utils.getMonth
import java.util.*

@Composable
fun ChooseDayContent(
    selectedDate: Calendar?,
    currentDate: Calendar,
    changeSelectedDate: (day: Calendar) -> Unit,
    calendarTheme: CalendarTheme
) {
    var days by remember { mutableStateOf(mutableListOf<Calendar>()) }
    val weeks = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    days = getDays(currentDate)
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