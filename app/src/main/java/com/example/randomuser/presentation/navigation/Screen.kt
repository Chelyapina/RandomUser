package com.example.randomuser.presentation.navigation

import com.example.randomuser.presentation.navigation.Screen.Companion.Routes.ONE_USER
import com.example.randomuser.presentation.navigation.Screen.Companion.Routes.USERS_LIST

sealed class Screen(val route : String) {
    object UsersList : Screen(USERS_LIST)
    object OneUser : Screen(ONE_USER) {
        fun createRoute(userId : String) = "one_user/$userId"
    }

    companion object {
        private object Routes {
            const val USERS_LIST = "users_list"
            const val ONE_USER = "one_user/{userId}"
        }
    }
}