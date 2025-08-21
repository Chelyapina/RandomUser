package com.example.randomuser.data.usecases

import com.example.randomuser.domain.UserRepository
import com.example.randomuser.domain.entities.User
import com.example.randomuser.domain.usecases.GetUsersUseCase
import com.example.randomuser.domain.util.Result
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(
    private val repository : UserRepository
) : GetUsersUseCase {

    override suspend operator fun invoke() : Result<List<User>> {
        return repository.getUsers()
    }
}