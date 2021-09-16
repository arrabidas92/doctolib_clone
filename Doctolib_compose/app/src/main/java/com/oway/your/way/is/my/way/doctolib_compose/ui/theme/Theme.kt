package com.oway.your.way.`is`.my.way.doctolib_compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = LightBlue300,
    primaryVariant = LightBlue800,
    secondary = LightBlue600,
    secondaryVariant = DarkBlue900
)

private val LightColorPalette = lightColors(
    primary = LightBlue600,
    primaryVariant = LightBlue800,
    secondary = LightBlue300,
    secondaryVariant = DarkBlue900,
    onPrimary = LightBlue900
)

@Composable
fun Doctolib_composeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}