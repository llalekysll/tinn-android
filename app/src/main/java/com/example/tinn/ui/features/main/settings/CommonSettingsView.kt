package com.example.tinn.ui.features.main.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tinn.data.modelForJSON.ResponceDataUserModel
import com.example.tinn.ui.components.CheckBoxWithLabel
import com.example.tinn.ui.components.TextWithCaption
import com.example.tinn.ui.components.textFields.TextFieldEmailWithButtonChange
import com.example.tinn.ui.components.textFields.TextFieldPhoneNumberWithButtonChange
import com.example.tinn.ui.components.textFields.TextFieldsWithButtonChange
import com.example.tinn.viewModel.UserViewModel
import java.util.*

@Composable
fun CommonSettingsView(
    viewModel: UserViewModel,
    user: ResponceDataUserModel,
    changeTheme: () -> Unit
) {
    val viewModel: UserViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var email by remember { mutableStateOf("skat@gmail.com") }

        var phone by remember { mutableStateOf(user.userProfiles.phone ?: "") }
        var url by remember { mutableStateOf(user.userProfiles.url ?: "") }
        var language by remember { mutableStateOf("") }
        var isCreatedContent by remember { mutableStateOf(true) }
        var isShowRecommended by remember { mutableStateOf(true) }
        var isShowPopular by remember { mutableStateOf(true) }

        TextWithCaption(caption = "Тема") {
            TextButton(onClick = { changeTheme() }) {
                val isLight = MaterialTheme.colors.isLight
                Text(
                    text = if (isLight) "Светлая" else "Темная",
                    modifier = Modifier.padding(start = 4.dp),
                    color = MaterialTheme.colors.primary,
                    textDecoration = TextDecoration.Underline
                )
            }
        }

        TextWithCaption(caption = "Тип аккаунта") {
            CheckBoxWithLabel(
                label = "Создатель контента",
                checked = isCreatedContent,
                onCheckedChanged = { isCreatedContent = isCreatedContent.not() }
            )
        }

        TextWithCaption(caption = "Настройки контента") {
            Column {
                CheckBoxWithLabel(
                    label = "Показывать блок \"Рекомендуемые\"",
                    checked = isShowRecommended,
                    onCheckedChanged = { isShowRecommended = isShowRecommended.not() }
                )

                CheckBoxWithLabel(
                    label = "Показывать блок \"Популярное\"",
                    checked = isShowPopular,
                    onCheckedChanged = { isShowPopular = isShowPopular.not() }
                )
            }
        }

        TextFieldEmailWithButtonChange(
            email = email,
            onValueChange = { email = it },
            onSave = { }
        )


        TextFieldPhoneNumberWithButtonChange(
            value = phone,
            onSave = { viewModel.putUserInfo(hashMapOf(Pair("phone", phone))) },
            onValueChange = { phone = it }
        )

        TextFieldsWithButtonChange(
            value = url,
            onSave = { viewModel.putUserInfo(hashMapOf(Pair("url", url))) },
            onValueChange = { url = it },
            caption = "Адрес профиля"
        )
    }
}



