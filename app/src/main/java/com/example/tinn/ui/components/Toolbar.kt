package com.example.tinn.ui.components

import android.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.tinn.R
import com.example.tinn.ui.theme.Red
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun Toolbar(
    exit: () -> Unit,
    openProfile: () -> Unit,
    openSettings: () -> Unit
) {
    TopAppBar(elevation = 20.dp, backgroundColor = MaterialTheme.colors.surface) {
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

                GlideImage(
                    imageModel = "https://tinn.io/images/avatar2.jpg",
                    Modifier
                        .padding(end = 8.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable { isExpand = true },
                )
            }

            DropdownMenu(
                expanded = isExpand,
                onDismissRequest = { isExpand = false },
                offset = DpOffset((-100).dp, 0.dp),
                modifier = Modifier.background(MaterialTheme.colors.surface)
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
                            color = if (it == "Выход") Red else MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
    }
}