package com.example.randomuser.presentation.oneUser.components

import androidx.compose.foundation.border
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import com.example.randomuser.presentation.commonComponents.UserImage
import com.example.randomuser.presentation.oneUser.OneUserUi
import com.example.randomuser.presentation.users.DialogState
import com.example.randomuser.presentation.utils.DesignConstants
import com.example.randomuser.presentation.utils.DesignConstants.THICKNESS_SIZE
import com.example.randomuser.presentation.utils.GenderColors
import com.example.randomuser.presentation.utils.toGenderColors

@Composable
internal fun OneUserContent(
    user : OneUserUi ,
    modifier : Modifier = Modifier ,
    onEmailClick : (String) -> Unit = {} ,
    onPhoneClick : (String) -> Unit = {} ,
    onCoordinatesClick : (String , String) -> Unit = { _ , _ -> }
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(DesignConstants.MEDIUM_PADDING)
            .verticalScroll(rememberScrollState()) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val genderColors = user.gender.toGenderColors()

        ImageInfoCard(user , genderColors)

        Spacer(modifier = Modifier.height(DesignConstants.LARGE_PADDING))

        UserInfoCard(user , genderColors)

        Spacer(modifier = Modifier.height(DesignConstants.LARGE_PADDING))

        ContactInfoCard(
            user = user ,
            genderColors = genderColors ,
            onEmailClick = onEmailClick ,
            onPhoneClick = onPhoneClick ,
            onCoordinatesClick = onCoordinatesClick
        )
    }
}

@Composable
private fun ImageInfoCard(
    user : OneUserUi ,
    genderColors : GenderColors
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = DesignConstants.BORDER_SIZE ,
                color = genderColors.border ,
                shape = MaterialTheme.shapes.medium
            ) ,
        colors = CardDefaults.cardColors(
            containerColor = genderColors.background
        ) ,
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DesignConstants.LARGE_PADDING) ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserImage(
                user.picture ,
                genderColors.border ,
                size = DesignConstants.ONE_USER_IMAGE_SIZE
            )

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
private fun UserInfoCard(
    user : OneUserUi ,
    genderColors : GenderColors
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = DesignConstants.BORDER_SIZE ,
                color = genderColors.border ,
                shape = MaterialTheme.shapes.medium
            ) ,
        colors = CardDefaults.cardColors(
            containerColor = genderColors.background
        ) ,
    ) {
        Column(
            modifier = Modifier.padding(DesignConstants.MEDIUM_PADDING)
        ) {
            CardTitle(stringResource(R.string.personal_information))

            DateDetailItem(stringResource(R.string.date_of_birth) , user.dobDate , user.dobAge)

            HorizontalDivider(
                modifier = Modifier.padding(vertical = DesignConstants.ITEM_PADDING) ,
                thickness = THICKNESS_SIZE ,
                color = genderColors.border
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
private fun ContactInfoCard(
    user : OneUserUi ,
    genderColors : GenderColors ,
    onEmailClick : (String) -> Unit ,
    onPhoneClick : (String) -> Unit ,
    onCoordinatesClick : (String , String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = DesignConstants.BORDER_SIZE ,
                color = genderColors.border ,
                shape = MaterialTheme.shapes.medium
            ) ,
        colors = CardDefaults.cardColors(
            containerColor = genderColors.background
        ) ,
    ) {
        Column(
            modifier = Modifier.padding(DesignConstants.MEDIUM_PADDING)
        ) {
            CardTitle(stringResource(R.string.contact_information))

            ClickableDetailItem(
                icon = Icons.Default.Email ,
                label = stringResource(R.string.email) ,
                value = user.email ,
                onClick = { onEmailClick(user.email) }
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = DesignConstants.ITEM_PADDING) ,
                thickness = THICKNESS_SIZE ,
                color = genderColors.border
            )

            ClickableDetailItem(
                icon = Icons.Default.Call ,
                label = stringResource(R.string.phone) ,
                value = user.phone ,
                onClick = { onPhoneClick(user.phone) }
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = DesignConstants.ITEM_PADDING) ,
                thickness = THICKNESS_SIZE ,
                color = genderColors.border
            )

            ClickableDetailItem(
                icon = Icons.Default.LocationOn ,
                label = stringResource(R.string.address) ,
                value = user.location ,
                onClick = {
                    onCoordinatesClick(
                        user.coordinatesLatitude ,
                        user.coordinatesLongitude
                    )
                }
            )
        }
    }
}

@Composable
fun ErrorDialog(
    dialogState : DialogState? ,
    onDismiss : () -> Unit
) {
    dialogState?.let { state ->
        AlertDialog(
            onDismissRequest = onDismiss ,
            title = {
                Text(text = stringResource(state.titleRes))
            } ,
            text = {
                Text(text = stringResource(state.messageRes))
            } ,
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }
}
