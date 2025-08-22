package com.example.randomuser.presentation.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
    private var navController : NavHostController? = null

    fun bind(navController : NavHostController) {
        this.navController = navController
    }

    fun unbind() {
        this.navController = null
    }

    fun navigateToOneUser(userId : String) {
        navController?.navigate(Screen.OneUser.createRoute(userId))
    }

    fun navigateBack() {
        navController?.popBackStack()
    }
}