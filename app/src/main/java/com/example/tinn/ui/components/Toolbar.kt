package com.example.tinn.ui.components

import android.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tinn.R

@Composable
fun Toolbar() {
    TopAppBar(elevation = 20.dp, backgroundColor = Color.White) {
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

            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar),
                    contentDescription = "profile",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}