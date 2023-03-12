package com.example.tinn.ui.features.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tinn.ui.theme.AlphaBlack
import com.example.tinn.viewModel.UserViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.example.tinn.R

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: UserViewModel = viewModel()
    val user by viewModel.userInfo.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getUserInfo()
    })

    user?.let {
        Box(contentAlignment = Alignment.TopEnd) {
            val url = it.userProfiles.background
                ?: "https://tinn.io/images/profile_bg.jpg?139dbff8e258a4fb08c356ede6ef77d0"

            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                imageModel = url
            )

            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(top = 120.dp, end = 16.dp)
                    .background(color = AlphaBlack, shape = RoundedCornerShape(8.dp))

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Изменить фон",
                    modifier = Modifier.size(12.dp),
                    tint = Color.White
                )
            }

            CardInformation(navController = navController, it)
        }
    }
}