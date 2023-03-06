package com.example.tinn.ui.navigation

import com.example.tinn.R

sealed class Screens(val route: String) {
    object SignIn : Screens("signIn")
    object Registration : Screens("registration")
    object ConfirmEmail : Screens("confirmEmail")
    object Main : Screens("main")
}

sealed class MainScreens(val route: String, val idIcon: Int) {
    object Home : MainScreens("home", R.drawable.ic_home)
    object Popular : MainScreens("popular", R.drawable.ic_podium)
    object Forum : MainScreens("forum", R.drawable.ic_forum)
    object Application : MainScreens("application", R.drawable.ic_money)
    object Room : MainScreens("room", R.drawable.ic_room)
    object Marketplace : MainScreens("marketplace", R.drawable.ic_phone)
}
