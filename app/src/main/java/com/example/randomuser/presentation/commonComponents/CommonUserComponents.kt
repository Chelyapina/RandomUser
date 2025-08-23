package com.example.randomuser.presentation.commonComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.randomuser.R
import com.example.randomuser.presentation.utils.DesignConstants

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

@Composable
fun UserImage(
    imageUrl : String? ,
    genderColor : Color ,
    modifier : Modifier = Modifier ,
    size : Dp
) {
    val borderColor = genderColor
    val context = LocalContext.current

    Box(
        modifier = modifier
            .size(size) ,
        contentAlignment = Alignment.Center
    ) {
        if (!imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .crossfade(true)
                    .build() ,
                contentDescription = stringResource(R.string.user_image) ,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .border(DesignConstants.BORDER_SIZE , borderColor , CircleShape) ,
                contentScale = ContentScale.Crop ,
                placeholder = painterResource(R.drawable.baseline_account_circle)
            )
        } else {
            PlaceholderIcon(borderColor)
        }
    }
}

@Composable
private fun PlaceholderIcon(borderColor : Color) {
    Icon(
        imageVector = Icons.Default.AccountCircle ,
        contentDescription = stringResource(R.string.user_image_error) ,
        tint = borderColor ,
        modifier = Modifier.fillMaxSize()
    )
}
