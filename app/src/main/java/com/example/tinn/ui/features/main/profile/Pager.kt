package com.example.tinn.ui.features.main.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tinn.ui.components.TextWithUnderLine

@Composable
fun Pager() {
    val pagers = listOf("MY ROOM", "ПЛЕЙЛИСТЫ", "ПОДПИСКИ", "О КАНАЛЕ")
    var selectedPage by remember { mutableStateOf(pagers[0]) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pagers) {
            val weight = if (selectedPage == it) FontWeight.Bold else FontWeight.Normal

            TextWithUnderLine(
                text = it,
                style = TextStyle(
                    fontWeight = weight,
                    fontSize = 16.sp,
                ),
                lineIsVisible = selectedPage == it,
                lineColor = Color.Black,
                modifier = Modifier.clickable { selectedPage = it })
        }
    }
}