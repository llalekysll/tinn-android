package com.example.tinn.ui.features.main.settings

enum class PagesSettings(val label: String) {
    COMMON("Общее"),
    PERSONAL("Личные данные"),
    PRIVACY("Приватность"),
    NOTIFICATIONS("Уведомления"),
    INFORMATION_CHANNEL("Информация о канале")
}

val pagesSettingsList = listOf(
    PagesSettings.COMMON,
    PagesSettings.PERSONAL,
    PagesSettings.PRIVACY,
    PagesSettings.NOTIFICATIONS,
    PagesSettings.INFORMATION_CHANNEL
)