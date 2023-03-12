package com.example.tinn.ui.components.calendarView

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tinn.ui.theme.CalendarAlertDialogTheme
import java.util.*

@Composable
fun CalendarAlertDialog(
    isVisible: Boolean,
    hideAlertDialog: () -> Unit,
    calendarAlertDialogTheme: CalendarAlertDialogTheme = CalendarAlertDialogTheme(),
    changeSelectedDate: (calendar: Calendar) -> Unit
) {
    var selectedDate by remember { mutableStateOf<Calendar?>(null) }

    if (isVisible) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(calendarAlertDialogTheme.backgroundColor)
                .clickable { hideAlertDialog() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card {
                Column(modifier = Modifier.clickable { }) {
                    CalendarView(
                        calendarTheme = calendarAlertDialogTheme.calendarTheme,
                        selectedDate = selectedDate,
                        changeSelectedDate = { selectedDate = it })

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            modifier = Modifier.width(100.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = calendarAlertDialogTheme.colorButton
                            ),
                            enabled = selectedDate != null,
                            onClick = {
                                changeSelectedDate(selectedDate!!)
                                hideAlertDialog()
                            }
                        ) {
                            Text(text = "Ок")
                        }

                        Button(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .width(100.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = calendarAlertDialogTheme.colorButtonCansel
                            ),
                            onClick = {
                                hideAlertDialog()
                            }) {
                            Text(text = "Отмена")
                        }
                    }
                }
            }
        }
    }
}