package com.example.randomuser.presentation.oneUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.domain.usecases.GetUserByIdUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OneUserScreenViewModel @AssistedInject constructor(
    private val getUserByIdUseCase : GetUserByIdUseCase ,
    @Assisted private val userId : String
) : ViewModel() {

    private val _userState = MutableStateFlow(OneUserState(isLoading = true))
    val userState : StateFlow<OneUserState> = _userState

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            val result = getUserByIdUseCase(userId)
            _userState.value = OneUserState(
                isLoading = false ,
                user = result?.toOneUserUi()
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(userId : String) : OneUserScreenViewModel
    }
}