package com.example.tinn.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.emailIfValid(): Boolean {
    return (this.isEmpty() || this.matches(Regex("\\S*@\\S*[.]\\S*")))
}

fun Calendar.getDate() = this.get(Calendar.DATE)
fun Calendar.getMonth() = this.get(Calendar.MONTH)
fun Calendar.getYear() = this.get(Calendar.YEAR)

fun Calendar.parseDateToShortString(): String? {
    val format = SimpleDateFormat("EE. dd MMM")
    return format.format(this.time)
}

fun Calendar.getDayOfWeek() = this.get(Calendar.DAY_OF_WEEK)