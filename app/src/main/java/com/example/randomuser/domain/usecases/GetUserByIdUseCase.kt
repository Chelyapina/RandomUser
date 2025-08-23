package com.example.randomuser.domain.usecases

import com.example.randomuser.domain.entities.User

interface GetUserByIdUseCase {
    suspend operator fun invoke(userId : String) : User?
}