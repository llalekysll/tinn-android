package com.example.tinn.ui.components.calendarView

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CalendarAlertDialogTheme (
    val backgroundColor: Color = Color(0, 0, 0, 0),
    val colorButton: Color = Color(0xFF78AEAE),
    val width: Dp = 100.dp,
    val calendarTheme: CalendarTheme = CalendarTheme(),
)
data class CalendarTheme(
    val calendarItemTheme: CalendarItemTheme = CalendarItemTheme(),
    val calendarHeaderTheme: CalendarHeaderTheme = CalendarHeaderTheme(),
    val backgroundColor: Color = Color(0, 0, 0, 0),
)

data class CalendarHeaderTheme(
    val headerBackgroundColor: Color = Color(0, 0, 0, 0),
    val headerTextColor: Color = Color(0xFF494949),
    val headerTextStyle: TextStyle = TextStyle.Default,
    val headerTextSize: TextUnit = 24.sp,
    val headerTextWeight: FontWeight = FontWeight.Bold
)

data class CalendarItemTheme(
    val textSize: TextUnit = 18.sp,
    val textStyle: TextStyle = TextStyle.Default,
    val dayShape: RoundedCornerShape = CircleShape,
    val dayBackgroundColor: Color = Color.White,
    val elevationDay: Dp = 5.dp,
    val selectedDayBackgroundColor: Color = Color(0xFF78AEAE),
    val dayValueTextColor: Color = Color.Black,
    val selectedDayValueTextColor: Color = Color.White,
)