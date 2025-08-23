package com.example.randomuser.presentation.commonScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.randomuser.R
import com.example.randomuser.domain.util.ErrorType
import com.example.randomuser.presentation.utils.DesignConstants
import com.example.randomuser.presentation.users.UserUi

@Composable
fun ErrorScreen(
    errorType : ErrorType ,
    localData : List<UserUi>? ,
    onRetry : () -> Unit ,
    onUseLocal : (() -> Unit)? ,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = when (errorType) {
                ErrorType.NO_INTERNET -> stringResource(R.string.error_no_internet)
                ErrorType.SERVER_ERROR -> stringResource(R.string.error_server)
                ErrorType.UNKNOWN_ERROR -> stringResource(R.string.error_unknown)
            } ,
            textAlign = TextAlign.Center ,
            modifier = Modifier.padding(DesignConstants.LARGE_PADDING)
        )

        Spacer(modifier = Modifier.height(DesignConstants.LARGE_PADDING))

        Button(onClick = onRetry) {
            Text(text = stringResource(R.string.retry))
        }

        if (onUseLocal != null && localData != null) {
            Spacer(modifier = Modifier.height(DesignConstants.MEDIUM_PADDING))
            Button(onClick = onUseLocal) {
                Text(text = stringResource(R.string.use_local_data))
            }
        }
    }
}
