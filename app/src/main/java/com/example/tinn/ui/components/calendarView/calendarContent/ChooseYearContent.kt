package com.example.tinn.ui.components.calendarView.calendarContent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tinn.ui.components.calendarView.CalendarItemYearTheme

private fun getYears(topYear: Int): List<Int> {
    val tempYears = mutableListOf<Int>()
    for (i in (topYear..topYear + 150)) tempYears.add(i)
    return tempYears.toList()
}

@Composable
fun ChooseYearContent(
    currentYear: Int,
    theme: CalendarItemYearTheme,
    changeYear: (year: Int) -> Unit
) {
    var years by remember { mutableStateOf<List<Int>>(emptyList()) }

    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = Unit, block = {
        years = getYears(currentYear - 130)
        scrollState.scrollToItem(125)
    })

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(years) {
            val isSelected = it == currentYear

            Text(
                text = it.toString(),
                style = MaterialTheme.typography.h5,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                fontSize = if (isSelected) theme.selectedSize else theme.unSelectedSize,
                color = if (isSelected) theme.selectedColor else theme.unSelectedColor,
                modifier = Modifier.clickable { changeYear(it) }
            )
        }
    }
}

