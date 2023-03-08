package com.example.tinn.ui.features.main.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tinn.R
import com.example.tinn.viewModel.UserViewModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalTextApi::class)
@Composable
fun Avatar(avatar: String?) {
    val viewModel: UserViewModel = viewModel()

    if (avatar != null) {
        GlideImage(
            imageModel = avatar,
            Modifier
                .clip(CircleShape)
                .padding(16.dp)
                .size(80.dp)
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.ic_avatar),
            contentDescription = "avatar",
            modifier = Modifier
                .padding(16.dp)
                .size(80.dp)
                .clickable {  }
        )
    }
}