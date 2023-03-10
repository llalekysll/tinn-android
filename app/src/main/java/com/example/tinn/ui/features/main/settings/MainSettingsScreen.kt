package com.example.tinn.ui.features.main.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tinn.ui.components.TextWithUnderLine
import com.example.tinn.viewModel.UserViewModel

@Composable
fun MainSettingsScreen(changeTheme: () -> Unit) {
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
                backgroundColor = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(8.dp),
                elevation= 8.dp
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(pagesSettingsList) {
                            ItemCategoryPages(it, selectedPage == it) { selectedPage = it }
                        }
                    }

                    when (selectedPage) {
                        PagesSettings.COMMON -> CommonSettingsView(viewModel, it, changeTheme)
                        else -> {}
                    }
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
        TextWithUnderLine(
            text = page.label,
            style = TextStyle(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            ),
            lineIsVisible = isSelected,
            modifier = Modifier.clickable { choosePage() },
        )
    }
}

