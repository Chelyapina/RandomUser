package com.example.randomuser.presentation.oneUser.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.randomuser.presentation.DesignConstants
import com.example.randomuser.presentation.commonComponents.TextWithIcon

@Composable
fun ClickableDetailItem(
    icon : ImageVector ,
    label : String ,
    value : String ,
    onClick : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = DesignConstants.ITEM_PADDING) ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = label ,
                style = MaterialTheme.typography.labelMedium ,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(DesignConstants.MEDIUM_PADDING))

            TextWithIcon(icon , value)
        }
    }
}

@Composable
fun CardTitle(text : String) {
    Text(
        text = text ,
        style = MaterialTheme.typography.titleMedium ,
        modifier = Modifier.padding(bottom = DesignConstants.ITEM_PADDING)
    )
}

@Composable
fun DateDetailItem(
    label : String ,
    date : String ,
    age : String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = DesignConstants.ITEM_PADDING)
    ) {
        Text(
            text = label ,
            style = MaterialTheme.typography.labelMedium ,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(DesignConstants.MEDIUM_PADDING))

        Row {
            Text(
                text = date ,
                style = MaterialTheme.typography.bodyLarge ,
                modifier = Modifier.padding(DesignConstants.MEDIUM_PADDING)
            )
            Text(
                text = age ,
                style = MaterialTheme.typography.bodyLarge ,
                modifier = Modifier.padding(DesignConstants.MEDIUM_PADDING)
            )
        }
    }
}