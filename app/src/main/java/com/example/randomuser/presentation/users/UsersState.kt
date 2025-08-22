package com.example.randomuser.presentation.users

import com.example.randomuser.domain.util.ErrorType

sealed class UsersState {
    object Loading : UsersState()
    data class Success(val users : List<UserUi> , val isLocal : Boolean) : UsersState()
    data class Error(val errorType : ErrorType , val localData : List<UserUi>? = null) :
        UsersState()
}
