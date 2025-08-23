package com.example.randomuser.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomuser.di.modules.DaggerViewModelFactory
import com.example.randomuser.presentation.oneUser.OneUserScreen
import com.example.randomuser.presentation.oneUser.OneUserScreenViewModel
import com.example.randomuser.presentation.users.UsersScreen
import com.example.randomuser.presentation.users.UsersScreenViewModel


@Composable
fun AppNavGraph(
    navController : NavHostController = rememberNavController() ,
    viewModelFactory : DaggerViewModelFactory ,
    navigator : Navigator
) {
    LaunchedEffect(navController) {
        navigator.bind(navController)
    }

    val usersScreenViewModel : UsersScreenViewModel = viewModel(
        factory = viewModelFactory
    )

    NavHost(
        navController = navController ,
        startDestination = Screen.UsersList.route
    ) {
        composable(Screen.UsersList.route) {
            UsersScreen(
                viewModel = usersScreenViewModel ,
                onUserClick = { userId -> usersScreenViewModel.onUserClick(userId) }
            )

            LaunchedEffect(usersScreenViewModel) {
                usersScreenViewModel.navigationEvents.collect { event ->
                    when (event) {
                        is NavigationEvent.NavigateToOneUser ->
                            navigator.navigateToOneUser(event.userId)
                    }
                }
            }
        }

        composable(Screen.OneUser.route) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userDetailViewModel : OneUserScreenViewModel =
                viewModelFactory.createOneUserViewModel(userId)

            OneUserScreen(
                viewModel = userDetailViewModel ,
                onBack = { navigator.navigateBack() }
            )
        }
    }
}
