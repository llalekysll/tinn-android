package com.example.tinn.ui.features.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tinn.R
import com.example.tinn.data.modelForJSON.ResponceDataUserModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CardInformation(navController: NavController, user: ResponceDataUserModel) {
    Card(
        elevation = 16.dp,
        shape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 180.dp),
    ) {
        Column {
            val avatar = user.userProfiles.avatar
            Avatar(avatar)
        }
    }
}