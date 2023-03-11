package com.example.tinn.utils

import java.util.*

fun String.emailIfValid(): Boolean {
    return (this.isEmpty() || this.matches(Regex("\\S*@\\S*[.]\\S*")))
}

fun Calendar.getDate() = this.get(Calendar.DATE)
fun Calendar.getMonth() = this.get(Calendar.MONTH)

fun Calendar.getDayOfWeek() = this.get(Calendar.DAY_OF_WEEK)