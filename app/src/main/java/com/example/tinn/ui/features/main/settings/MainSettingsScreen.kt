package com.example.tinn.ui.features.main.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tinn.ui.components.HorizontalSpacer
import com.example.tinn.ui.theme.Blue
import com.example.tinn.viewModel.UserViewModel

@Composable
fun MainSettingsScreen() {
    val viewModel: UserViewModel = viewModel()
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getUserInfo()
    })

    var selectedPage by remember { mutableStateOf(PagesSettings.COMMON) }
    val user by viewModel.userInfo.observeAsState()

    user?.let {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = 8.dp
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(pagesSettingsList) {
                            ItemCategoryPages(it, selectedPage == it) { selectedPage = it }
                        }
                    }

                    CommonSettingsView(viewModel, it)
                }
            }
        }
    }
}

@Composable
fun ItemCategoryPages(
    page: PagesSettings,
    isSelected: Boolean,
    choosePage: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = page.label,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.clickable { choosePage() },
            style = TextStyle()
        )

        if (isSelected) {
            HorizontalSpacer(colorSpacer = Blue, modifier = Modifier.width(60.dp))
        }
    }
}

