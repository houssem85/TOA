@file:Suppress("MagicNumber")
package com.houssem85.toa.ui.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

private val PrimaryBlue = Color(0xFF0A3751)
private val SecondaryOrange = Color(0xFFFC8138)
private val BackgroundBlue = Color(0xFFF4F4F8)
private val OnBackgroundBlack = Color(0xFF282828)

val lightColorPalette = lightColors(
    primary = PrimaryBlue,
    onPrimary = Color.White,
    secondary = SecondaryOrange,
    onSecondary = Color.White,
    background = BackgroundBlue,
    onBackground = OnBackgroundBlack,
    surface = Color.White ,
    onSurface = PrimaryBlue
)
