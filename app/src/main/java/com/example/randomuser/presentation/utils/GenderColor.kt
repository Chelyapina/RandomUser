package com.example.randomuser.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.randomuser.domain.entities.GenderEnum
import com.example.randomuser.ui.theme.LocalExtendedColors

@Composable
fun GenderEnum.toGenderColors() : GenderColors {
    val extendedColors = LocalExtendedColors.current
    val baseColor = when (this) {
        GenderEnum.MALE -> extendedColors.maleColor
        GenderEnum.FEMALE -> extendedColors.femaleColor
    }

    return GenderColors(
        border = baseColor ,
        background = baseColor.copy(alpha = 0.2f)
    )
}

data class GenderColors(
    val border : Color ,
    val background : Color
)