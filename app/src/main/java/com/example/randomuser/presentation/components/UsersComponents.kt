package com.example.randomuser.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.randomuser.presentation.DesignConstants
import com.example.randomuser.presentation.users.UserUi

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
                onClick = { onUserClick }
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
    Card(
        modifier = modifier
            .padding(DesignConstants.ITEM_PADDING) ,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) ,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(DesignConstants.CARD_PADDING) ,
            verticalAlignment = Alignment.CenterVertically
        ) {
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

@Composable
private fun TextWithIcon(
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
