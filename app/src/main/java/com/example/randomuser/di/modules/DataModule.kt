package com.example.randomuser.di.modules

import com.example.randomuser.data.UserRepositoryImplementation
import com.example.randomuser.data.mappers.UserDbMapper
import com.example.randomuser.data.mappers.UserNetworkMapper
import com.example.randomuser.data.network.RandomUserApiService
import com.example.randomuser.data.storage.UsersDao
import com.example.randomuser.data.usecases.GetLocalUsersUseCaseImpl
import com.example.randomuser.data.usecases.GetUserByIdUseCaseImpl
import com.example.randomuser.data.usecases.GetUsersUseCaseImpl
import com.example.randomuser.data.usecases.RefreshUsersUseCaseImpl
import com.example.randomuser.domain.UserRepository
import com.example.randomuser.domain.usecases.GetLocalUsersUseCase
import com.example.randomuser.domain.usecases.GetUserByIdUseCase
import com.example.randomuser.domain.usecases.GetUsersUseCase
import com.example.randomuser.domain.usecases.RefreshUsersUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class ,
        DatabaseModule::class ,
        MapperModule::class
    ]
)
class DataModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        apiService : RandomUserApiService ,
        userNetworkMapper : UserNetworkMapper ,
        userDbMapper : UserDbMapper ,
        usersDao : UsersDao
    ) : UserRepository {
        return UserRepositoryImplementation(
            apiService ,
            userNetworkMapper ,
            userDbMapper ,
            usersDao
        )
    }

    @Provides
    fun provideGetUsersUseCase(repository : UserRepository) : GetUsersUseCase {
        return GetUsersUseCaseImpl(repository)
    }

    @Provides
    fun provideRefreshUsersUseCase(repository : UserRepository) : RefreshUsersUseCase {
        return RefreshUsersUseCaseImpl(repository)
    }

    @Provides
    fun provideGetLocalUsersUseCase(repository : UserRepository) : GetLocalUsersUseCase {
        return GetLocalUsersUseCaseImpl(repository)
    }

    @Provides
    fun provideGetUserByIdUseCase(repository : UserRepository) : GetUserByIdUseCase {
        return GetUserByIdUseCaseImpl(repository)
    }
}