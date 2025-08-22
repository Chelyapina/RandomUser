package com.example.randomuser.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomuser.di.modules.DaggerViewModelFactory
import com.example.randomuser.presentation.users.UsersScreen
import com.example.randomuser.presentation.users.UsersScreenViewModel

object Routes {
    const val USERS_LIST = "users_list"
}

@Composable
fun AppNavGraph(
    viewModelFactory : DaggerViewModelFactory ,
    modifier : Modifier = Modifier
) {
    val navController = rememberNavController()
    val usersScreenViewModel : UsersScreenViewModel = viewModel(factory = viewModelFactory)

    NavHost(
        navController = navController ,
        startDestination = Routes.USERS_LIST ,
        modifier = modifier
    ) {
        composable(Routes.USERS_LIST) {
            UsersScreen(
                viewModel = usersScreenViewModel ,
                onUserClick = { } ,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
