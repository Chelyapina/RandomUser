package com.example.randomuser.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.randomuser.data.storage.entities.UserDb

@Database(
    entities = [UserDb::class],
    version = DatabaseConstants.DATABASE_VERSION,
    exportSchema = DatabaseConstants.EXPORT_SCHEMA
)
@TypeConverters(DateWithAgeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}