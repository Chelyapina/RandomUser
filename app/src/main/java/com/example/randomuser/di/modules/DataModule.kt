package com.example.randomuser.di.modules

import com.example.randomuser.data.usecases.GetLocalUsersUseCaseImpl
import com.example.randomuser.data.usecases.GetUsersUseCaseImpl
import com.example.randomuser.data.usecases.RefreshUsersUseCaseImpl
import com.example.randomuser.domain.UserRepository
import com.example.randomuser.domain.usecases.GetLocalUsersUseCase
import com.example.randomuser.domain.usecases.GetUsersUseCase
import com.example.randomuser.domain.usecases.RefreshUsersUseCase
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        NetworkModule::class ,
        DatabaseModule::class ,
        MapperModule::class
    ]
)
class DataModule {
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
}