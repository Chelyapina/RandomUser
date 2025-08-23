package com.example.randomuser.presentation.users

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.randomuser.presentation.commonComponents.TextWithIcon
import com.example.randomuser.presentation.commonComponents.UserImage
import com.example.randomuser.presentation.utils.DesignConstants
import com.example.randomuser.presentation.utils.toGenderColors

@Composable
fun UserList(
    users : List<UserUi> ,
    onUserClick : (String) -> Unit ,
    modifier : Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(users) { user ->
            UserItem(
                user = user ,
                modifier = Modifier.fillMaxWidth() ,
                onClick = { onUserClick(user.userId) }
            )
        }
    }
}

@Composable
private fun UserItem(
    user : UserUi ,
    modifier : Modifier = Modifier ,
    onClick : () -> Unit
) {
    val genderColors = user.gender.toGenderColors()

    Card(
        modifier = modifier
            .padding(DesignConstants.MEDIUM_PADDING)
            .border(
                width = DesignConstants.BORDER_SIZE ,
                color = genderColors.border ,
                shape = MaterialTheme.shapes.medium
            ) ,
        colors = CardDefaults.cardColors(
            containerColor = genderColors.background
        ) ,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(DesignConstants.CARD_PADDING) ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserImage(
                user.picture ,
                genderColors.border ,
                size = DesignConstants.USER_LIST_IMAGE_SIZE
            )

            Spacer(Modifier.width(DesignConstants.MEDIUM_PADDING))

            UserInfo(user)
        }
    }
}

@Composable
private fun UserInfo(user : UserUi) {
    Column {
        Text(
            text = "${user.nameFirst} ${user.nameLast}" ,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        TextWithIcon(Icons.Default.Place , text = user.location)
        TextWithIcon(Icons.Default.Call , text = user.phone)
    }
}
