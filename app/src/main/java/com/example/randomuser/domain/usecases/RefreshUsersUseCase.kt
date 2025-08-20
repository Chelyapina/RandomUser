package com.example.randomuser.domain.usecases

import com.example.randomuser.domain.entities.User
import com.example.randomuser.domain.util.Result

interface RefreshUsersUseCase {
    suspend operator fun invoke() : Result<List<User>>
}