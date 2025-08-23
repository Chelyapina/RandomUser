package com.example.randomuser.data

import com.example.randomuser.data.mappers.UserDbMapper
import com.example.randomuser.data.mappers.UserNetworkMapper
import com.example.randomuser.data.network.RandomUserApiService
import com.example.randomuser.data.storage.UsersDao
import com.example.randomuser.domain.UserRepository
import com.example.randomuser.domain.entities.User
import com.example.randomuser.domain.util.ErrorType
import com.example.randomuser.domain.util.Result
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(
    private val apiService : RandomUserApiService ,
    private val userNetworkMapper : UserNetworkMapper ,
    private val userDbMapper : UserDbMapper ,
    private val usersDao : UsersDao
) : UserRepository {
    override suspend fun getUsers() : Result<List<User>> {
        return try {
            val cachedUsers = usersDao.getAllUsers()
            if (cachedUsers.isNotEmpty()) {
                return Result.Success(userDbMapper.mapFromDbList(cachedUsers))
            }
            val response = apiService.getUsers()
            val users = userNetworkMapper.mapFromResponse(response)
            usersDao.clearAllUsers()
            usersDao.insertAllUsers(userDbMapper.mapToDbList(users))
            Result.Success(users)
        } catch (e : IOException) {
            val cachedUsers = usersDao.getAllUsers()
            if (cachedUsers.isNotEmpty()) {
                Result.Success(userDbMapper.mapFromDbList(cachedUsers))
            } else {
                Result.Error(ErrorType.NO_INTERNET)
            }
        } catch (e : HttpException) {
            val cachedUsers = usersDao.getAllUsers()
            if (cachedUsers.isNotEmpty()) {
                Result.Success(userDbMapper.mapFromDbList(cachedUsers))
            } else {
                Result.Error(ErrorType.SERVER_ERROR)
            }
        } catch (e : Exception) {
            val cachedUsers = usersDao.getAllUsers()
            if (cachedUsers.isNotEmpty()) {
                Result.Success(userDbMapper.mapFromDbList(cachedUsers))
            } else {
                Result.Error(ErrorType.UNKNOWN_ERROR)
            }
        }
    }

    override suspend fun refreshUsers() : Result<List<User>> {
        return try {
            val response = apiService.getUsers()
            val users = userNetworkMapper.mapFromResponse(response)
            usersDao.clearAllUsers()
            usersDao.insertAllUsers(userDbMapper.mapToDbList(users))
            Result.Success(users)
        } catch (e : IOException) {
            Result.Error(ErrorType.NO_INTERNET)
        } catch (e : HttpException) {
            Result.Error(ErrorType.SERVER_ERROR)
        } catch (e : Exception) {
            Result.Error(ErrorType.UNKNOWN_ERROR)
        }
    }

    override suspend fun getLocalUsers() : Result<List<User>> {
        return try {
            val cachedUsers = usersDao.getAllUsers()
            if (cachedUsers.isNotEmpty()) {
                Result.Success(userDbMapper.mapFromDbList(cachedUsers))
            } else {
                Result.Error(ErrorType.UNKNOWN_ERROR)
            }
        } catch (e : Exception) {
            Result.Error(ErrorType.UNKNOWN_ERROR)
        }
    }

    override suspend fun getUserById(userId : String) : User? {
        return usersDao.getUserById(userId)?.let { userDbMapper.mapFromDb(it) }
    }
}
