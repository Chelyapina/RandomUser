package com.example.randomuser.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomuser.data.storage.entities.UserDb

@Dao
interface UsersDao {
    @Query("SELECT * FROM ${DatabaseConstants.TABLE_USERS}")
    suspend fun getAllUsers() : List<UserDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users : List<UserDb>)

    @Query("DELETE FROM ${DatabaseConstants.TABLE_USERS}")
    suspend fun clearAllUsers()
}