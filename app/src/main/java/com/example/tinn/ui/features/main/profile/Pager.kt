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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Pager() {
    val pagers = listOf("My room", "Плейлисты", "Подписки", "О канале")
    var selectedPage by remember { mutableStateOf(pagers[0]) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pagers) {
            val weight = if (selectedPage == it) FontWeight.Bold else FontWeight.Normal
            val textDecoration =
                if (selectedPage == it) TextDecoration.Underline else TextDecoration.None

            Text(
                text = it,
                fontWeight = weight,
                fontSize = 20.sp,
                textDecoration = textDecoration,
                modifier = Modifier.clickable { selectedPage = it })
        }
    }
}