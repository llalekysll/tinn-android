package com.example.tinn.ui.components

import android.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.tinn.R
import com.example.tinn.ui.theme.Red

@Composable
fun Toolbar(
    exit: () -> Unit,
    openProfile: () -> Unit,
    openSettings: () -> Unit
) {
    TopAppBar(elevation = 20.dp, backgroundColor = Color.White) {
        var isExpand by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.End) {
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(100.dp)
                )

                IconButton(onClick = { isExpand = true }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_avatar),
                        contentDescription = "profile",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = isExpand,
                onDismissRequest = { isExpand = false },
                offset = DpOffset((-100).dp, 0.dp)
            ) {
                listOf("Мой канал", "Настройки", "Выход").forEach {
                    DropdownMenuItem(onClick = {
                        isExpand = false
                        when (it) {
                            "Настройки" -> openSettings()
                            "Мой канал" -> openProfile()
                            else -> exit()
                        }
                    }) {
                        Text(
                            text = it,
                            color = if (it == "Выход") Red else Color.Black
                        )
                    }
                }
            }
        }
    }
}