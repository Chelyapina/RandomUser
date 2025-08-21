package com.example.randomuser.di.modules

import android.content.Context
import androidx.room.Room
import com.example.randomuser.data.storage.AppDatabase
import com.example.randomuser.data.storage.DatabaseConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(context : Context) : AppDatabase {
        return Room.databaseBuilder(
            context ,
            AppDatabase::class.java ,
            DatabaseConstants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideUsersDao(database : AppDatabase) = database.usersDao()
}