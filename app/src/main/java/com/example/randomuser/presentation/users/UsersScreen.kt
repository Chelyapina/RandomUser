package com.example.randomuser.presentation.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.randomuser.presentation.commonScreens.ErrorScreen
import com.example.randomuser.presentation.commonScreens.LoadingScreen

@Composable
fun UsersScreen(
    viewModel : UsersScreenViewModel ,
    onUserClick : (String) -> Unit ,
    modifier : Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            if (state is UsersState.Success) {
                FloatingActionButton(
                    onClick = { viewModel.refreshUsers() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh ,
                        contentDescription = null
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (state) {
                is UsersState.Loading -> LoadingScreen(modifier)
                is UsersState.Success -> {
                    val successState = state as UsersState.Success
                    Box(modifier = modifier.fillMaxSize()) {
                        UserList(
                            users = successState.users ,
                            onUserClick
                        )
                    }
                }

                is UsersState.Error -> {
                    val errorState = state as UsersState.Error
                    ErrorScreen(
                        errorType = errorState.errorType ,
                        localData = errorState.localData ,
                        onRetry = { viewModel.retry() } ,
                        onUseLocal = if (errorState.localData != null) {
                            { viewModel.useLocalData() }
                        } else {
                            null
                        } ,
                        modifier = modifier
                    )
                }
            }
        }
    }
}
