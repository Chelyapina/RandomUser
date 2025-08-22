package com.example.randomuser.data.usecases

import com.example.randomuser.domain.UserRepository
import com.example.randomuser.domain.entities.User
import com.example.randomuser.domain.usecases.RefreshUsersUseCase
import com.example.randomuser.domain.util.Result
import javax.inject.Inject

class RefreshUsersUseCaseImpl @Inject constructor(
    private val repository : UserRepository
) : RefreshUsersUseCase {

    override suspend operator fun invoke() : Result<List<User>> {
        return repository.refreshUsers()
    }
}