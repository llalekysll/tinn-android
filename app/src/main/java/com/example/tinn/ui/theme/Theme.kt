package com.example.tinn.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Blue,
    primaryVariant = LightBlue,
    error = Red,
    background = Color.White,
    onBackground = Gray,
    surface = Color.White,
    onSurface = DarkGray1
)

private val DarkColorPalette = darkColors(
    primary = Blue,
    primaryVariant = LightBlue,
    error = Red,
    background = DarkGray3,
    onBackground = DarkGray1,
    surface = DarkGray2,
    onSurface = Gray
)

@Composable
fun TinnTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}