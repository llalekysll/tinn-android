package com.example.tinn.ui.navigation

import com.example.tinn.R

sealed class Screens(val route: String) {
    object SignIn : Screens("signIn")
    object Registration : Screens("registration")
    object ConfirmEmail : Screens("confirmEmail")
    object InputInfoUser : Screens("inputInfoUser")
    object Main : Screens("main")
}

sealed class MainScreens(val route: String, val label: String, val idIcon: Int?) {
    object Home : MainScreens("home", "Главная", R.drawable.ic_home)
    object Popular : MainScreens("popular", "Популярное", R.drawable.ic_podium)
    object Forum : MainScreens("forum", "Форум", R.drawable.ic_forum)
    object Application : MainScreens("application", "Мои заявки", R.drawable.ic_money)
    object Room : MainScreens("room", "My room", R.drawable.ic_room)
    object Marketplace : MainScreens("marketplace", "Marketplace", R.drawable.ic_phone)
    object Profile : MainScreens("profile", "", null)
    object Settings : MainScreens("settings", "", null)
}

val mainScreensList = mutableListOf(
    MainScreens.Home,
    MainScreens.Popular,
    MainScreens.Forum,
    MainScreens.Application,
    MainScreens.Room,
    MainScreens.Marketplace,
)