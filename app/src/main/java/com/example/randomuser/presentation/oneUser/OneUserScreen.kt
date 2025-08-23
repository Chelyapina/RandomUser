package com.example.randomuser.presentation.oneUser

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.randomuser.R
import com.example.randomuser.presentation.commonScreens.LoadingScreen
import com.example.randomuser.presentation.oneUser.components.ErrorDialog
import com.example.randomuser.presentation.oneUser.components.OneUserContent
import com.example.randomuser.presentation.utils.DesignConstants

@Composable
fun OneUserScreen(
    viewModel : OneUserScreenViewModel ,
    onBack : () -> Unit
) {
    val userState by viewModel.userState.collectAsState()
    val dialogState by viewModel.dialogState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .statusBarsPadding() ,
                backgroundColor = MaterialTheme.colorScheme.surface ,
                contentColor = MaterialTheme.colorScheme.onSurface ,
                elevation = DesignConstants.ITEM_PADDING ,
                title = {
                    Text(
                        stringResource(R.string.user_details) ,
                        style = MaterialTheme.typography.headlineSmall
                    )
                } ,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack ,
                            contentDescription = stringResource(R.string.back) ,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { padding ->
        ErrorDialog(
            dialogState = dialogState ,
            onDismiss = { viewModel.onDialogDismiss() }
        )
        if (userState.isLoading) {
            LoadingScreen()
        } else if (userState.user != null) {
            OneUserContent(
                user = userState.user!! ,
                modifier = Modifier.padding(padding) ,
                onEmailClick = { email -> viewModel.onEmailClick(email) } ,
                onPhoneClick = { phone -> viewModel.onPhoneClick(phone) } ,
                onCoordinatesClick = { lat , long ->
                    viewModel.onCoordinatesClick(lat , long)
                }
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding) ,
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.user_not_found))
            }
        }
    }
}
