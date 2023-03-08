package com.example.tinn.ui.features.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tinn.ui.theme.Gray
import com.example.tinn.viewModel.UserViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: UserViewModel = viewModel()
    val user by viewModel.userInfo.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getUserInfo()
    })

    user?.let {
        Box {
            GlideImage(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                imageModel = "https://s0.rbk.ru/v6_top_pics/media/img/9/16/756619467602169.jpg")

            Card(
                elevation = 16.dp,
                shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
                modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(top = 180.dp),
            ) {

            }
        }
    }
}