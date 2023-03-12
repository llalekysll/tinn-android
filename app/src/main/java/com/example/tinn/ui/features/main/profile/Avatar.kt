package com.example.tinn.ui.features.main.profile

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tinn.R
import com.example.tinn.utils.StatusRequestFactory
import com.example.tinn.viewModel.UserViewModel
import com.skydoves.landscapist.glide.GlideImage

typealias status = StatusRequestFactory.StatusType

@Composable
fun Avatar(avatar: String) {
    val viewModel: UserViewModel = viewModel()

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    imageUri?.let {
        viewModel.loadAvatar(it)
    }

    val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val statusRequest by viewModel.requestStatus.observeAsState()
    if (statusRequest?.status == status.SUCCESS) {

    }
    if (statusRequest?.status == status.LOADING) CircularProgressIndicator()

        GlideImage(
            imageModel = avatar,
            Modifier
                .padding(16.dp)
                .size(80.dp)
                .clip(CircleShape),
        )
}