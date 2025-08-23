package com.example.randomuser.data.usecases

import com.example.randomuser.domain.UserRepository
import com.example.randomuser.domain.entities.User
import com.example.randomuser.domain.usecases.GetUserByIdUseCase
import javax.inject.Inject

class GetUserByIdUseCaseImpl @Inject constructor(
    private val repository : UserRepository
) : GetUserByIdUseCase {

    override suspend operator fun invoke(userId : String) : User? {
        return repository.getUserById(userId)
    }
}