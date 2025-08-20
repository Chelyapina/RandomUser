package com.example.randomuser.domain

import com.example.randomuser.domain.entities.User

interface UserRepository {
    suspend fun getUsers() : Result<List<User>>
    suspend fun refreshUsers() : Result<List<User>>
    suspend fun getLocalUsers() : Result<List<User>>
}