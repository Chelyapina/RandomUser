package com.example.randomuser.data.storage

import androidx.room.TypeConverter
import com.example.randomuser.data.storage.entities.DateWithAgeDb
import com.google.gson.Gson

class DateWithAgeConverter {
    private val gson by lazy { Gson() }

    @TypeConverter
    fun fromDateWithAgeDb(dateWithAgeDb : DateWithAgeDb) : String {
        return gson.toJson(dateWithAgeDb)
    }

    @TypeConverter
    fun toDateWithAgeDb(json : String) : DateWithAgeDb {
        return gson.fromJson(json , DateWithAgeDb::class.java)
    }
}