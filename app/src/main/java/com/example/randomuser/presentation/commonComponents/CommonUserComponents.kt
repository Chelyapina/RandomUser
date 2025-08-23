package com.example.randomuser.presentation.commonComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.randomuser.presentation.DesignConstants

@Composable
fun TextWithIcon(
    icon : ImageVector ,
    text : String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically ,
        modifier = Modifier.padding(vertical = DesignConstants.ITEM_PADDING / 2)
    ) {
        Icon(
            imageVector = icon ,
            contentDescription = null ,
            modifier = Modifier.size(DesignConstants.ICON_SIZE)
        )
        Spacer(Modifier.width(DesignConstants.MEDIUM_PADDING))
        Text(text = text)
    }
}
