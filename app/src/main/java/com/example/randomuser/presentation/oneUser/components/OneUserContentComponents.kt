package com.example.randomuser.presentation.oneUser.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.randomuser.R
import com.example.randomuser.presentation.DesignConstants
import com.example.randomuser.presentation.DesignConstants.THICKNESS_SIZE
import com.example.randomuser.presentation.oneUser.OneUserUi

@Composable
internal fun OneUserContent(
    user : OneUserUi ,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(DesignConstants.ITEM_PADDING)
            .verticalScroll(rememberScrollState()) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageInfoCard(user)

        Spacer(modifier = Modifier.height(DesignConstants.LARGE_PADDING))

        UserInfoCard(user)

        Spacer(modifier = Modifier.height(DesignConstants.LARGE_PADDING))

        ContactInfoCard(user)
    }
}

@Composable
private fun ImageInfoCard(user : OneUserUi) {
    Card(
        modifier = Modifier.fillMaxWidth() ,
        elevation = CardDefaults.cardElevation(defaultElevation = DesignConstants.ITEM_PADDING)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DesignConstants.LARGE_PADDING) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(DesignConstants.LARGE_PADDING))

            Text(
                text = "${user.nameFirst} ${user.nameLast}" ,
                style = MaterialTheme.typography.headlineMedium ,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun UserInfoCard(user : OneUserUi) {
    Card(
        modifier = Modifier.fillMaxWidth() ,
        elevation = CardDefaults.cardElevation(defaultElevation = DesignConstants.ITEM_PADDING)
    ) {
        Column(
            modifier = Modifier.padding(DesignConstants.MEDIUM_PADDING)
        ) {
            CardTitle(stringResource(R.string.personal_information))

            DateDetailItem(stringResource(R.string.date_of_birth) , user.dobDate , user.dobAge)

            HorizontalDivider(
                modifier = Modifier.padding(vertical = DesignConstants.ITEM_PADDING) ,
                thickness = THICKNESS_SIZE ,
            )

            DateDetailItem(
                stringResource(R.string.registered) ,
                user.registeredDate ,
                user.registeredAge
            )
        }
    }
}

@Composable
private fun ContactInfoCard(user : OneUserUi) {
    Card(
        modifier = Modifier.fillMaxWidth() ,
        elevation = CardDefaults.cardElevation(defaultElevation = DesignConstants.ITEM_PADDING)
    ) {
        Column(
            modifier = Modifier.padding(DesignConstants.MEDIUM_PADDING)
        ) {
            CardTitle(stringResource(R.string.contact_information))

            ClickableDetailItem(
                icon = Icons.Default.Email ,
                label = stringResource(R.string.email) ,
                value = user.email ,
                onClick = { }
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = DesignConstants.ITEM_PADDING) ,
                thickness = THICKNESS_SIZE ,
            )

            ClickableDetailItem(
                icon = Icons.Default.Call ,
                label = stringResource(R.string.phone) ,
                value = user.phone ,
                onClick = { }
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = DesignConstants.ITEM_PADDING) ,
                thickness = THICKNESS_SIZE ,
            )

            ClickableDetailItem(
                icon = Icons.Default.LocationOn ,
                label = stringResource(R.string.address) ,
                value = user.location ,
                onClick = { }
            )
        }
    }
}
