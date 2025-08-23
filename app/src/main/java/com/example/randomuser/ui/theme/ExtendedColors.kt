package com.example.randomuser.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object ExtendedLightColors {
    val maleColor = LightMale
    val femaleColor = LightFemale
}

object ExtendedDarkColors {
    val maleColor = DarkMale
    val femaleColor = DarkFemale
}

data class ExtendedColors(
    val maleColor : Color ,
    val femaleColor : Color
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        maleColor = ExtendedLightColors.maleColor ,
        femaleColor = ExtendedLightColors.femaleColor
    )
}