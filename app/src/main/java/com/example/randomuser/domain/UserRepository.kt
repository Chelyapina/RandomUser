package com.example.randomuser.domain

import com.example.randomuser.domain.entities.User
import com.example.randomuser.domain.util.Result

interface UserRepository {
    suspend fun getUsers() : Result<List<User>>
    suspend fun refreshUsers() : Result<List<User>>
    suspend fun getLocalUsers() : Result<List<User>>
    suspend fun getUserById(userId: String): User?
}