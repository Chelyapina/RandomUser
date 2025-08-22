package com.example.randomuser.presentation.navigation

sealed interface NavigationEvent {
    data class NavigateToOneUser(val userId : String) : NavigationEvent
}