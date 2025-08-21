package com.example.randomuser.data.usecases

import com.example.randomuser.domain.UserRepository
import com.example.randomuser.domain.entities.User
import com.example.randomuser.domain.usecases.GetLocalUsersUseCase
import com.example.randomuser.domain.util.Result
import javax.inject.Inject

class GetLocalUsersUseCaseImpl @Inject constructor(
    private val repository : UserRepository
) : GetLocalUsersUseCase {

    override suspend operator fun invoke() : Result<List<User>> {
        return repository.getLocalUsers()
    }
}