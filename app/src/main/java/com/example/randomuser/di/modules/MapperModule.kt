package com.example.randomuser.di.modules

import com.example.randomuser.data.mappers.UserDbMapper
import com.example.randomuser.data.mappers.UserNetworkMapper
import dagger.Module
import dagger.Provides

@Module
class MapperModule {
    @Provides
    fun provideNetworkMapper() : UserNetworkMapper {
        return UserNetworkMapper()
    }

    @Provides
    fun provideDbMapper() : UserDbMapper {
        return UserDbMapper()
    }
}