package com.example.randomuser.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.domain.usecases.GetLocalUsersUseCase
import com.example.randomuser.domain.usecases.GetUsersUseCase
import com.example.randomuser.domain.usecases.RefreshUsersUseCase
import com.example.randomuser.domain.util.ErrorType
import com.example.randomuser.domain.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersScreenViewModel @Inject constructor(
    private val getUsersUseCase : GetUsersUseCase ,
    private val refreshUsersUseCase : RefreshUsersUseCase ,
    private val getLocalUsersUseCase : GetLocalUsersUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UsersState>(UsersState.Loading)
    val state : StateFlow<UsersState> = _state

    private var cachedUsers : List<UserUi> = emptyList()
    private var isLoading = false

    init {
        loadUsers()
    }

    fun loadUsers() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            _state.value = UsersState.Loading
            when (val result = getUsersUseCase()) {
                is Result.Success -> {
                    cachedUsers = result.data.map { it.toUserUI() }
                    _state.value = UsersState.Success(users = cachedUsers , isLocal = false)
                }

                is Result.Error -> {
                    handleError(result.type)
                }
            }
            isLoading = false
        }
    }

    fun refreshUsers() {
        if (isLoading) return

        viewModelScope.launch {
            isLoading = true
            _state.value = UsersState.Loading
            when (val result = refreshUsersUseCase()) {
                is Result.Success -> {
                    cachedUsers = result.data.map { it.toUserUI() }
                    _state.value = UsersState.Success(users = cachedUsers , isLocal = false)
                }

                is Result.Error -> {
                    val localResult = getLocalUsersUseCase()
                    if (localResult is Result.Success) {
                        cachedUsers = localResult.data.map { it.toUserUI() }
                        _state.value = UsersState.Error(result.type , cachedUsers)
                    } else {
                        _state.value = UsersState.Error(result.type)
                    }
                }
            }
            isLoading = false
        }
    }

    fun retry() {
        refreshUsers()
    }

    fun useLocalData() {
        if (cachedUsers.isNotEmpty()) {
            _state.value = UsersState.Success(cachedUsers , isLocal = true)
        }
    }

    private fun handleError(errorType : ErrorType) {
        viewModelScope.launch {
            val localResult = getLocalUsersUseCase()
            if (localResult is Result.Success) {
                cachedUsers = localResult.data.map { it.toUserUI() }
                _state.value = UsersState.Error(errorType , cachedUsers)
            } else {
                _state.value = UsersState.Error(errorType)
            }
        }
    }
}
