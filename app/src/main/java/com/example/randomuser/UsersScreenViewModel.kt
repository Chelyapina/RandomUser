package com.example.randomuser

import androidx.lifecycle.ViewModel
import com.example.randomuser.domain.usecases.GetLocalUsersUseCase
import com.example.randomuser.domain.usecases.GetUsersUseCase
import com.example.randomuser.domain.usecases.RefreshUsersUseCase
import javax.inject.Inject

class UsersScreenViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val refreshUsersUseCase: RefreshUsersUseCase,
    private val getLocalUsersUseCase: GetLocalUsersUseCase
) : ViewModel() {

}