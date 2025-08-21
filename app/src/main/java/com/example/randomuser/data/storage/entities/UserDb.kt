package com.example.randomuser.data.storage.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomuser.data.storage.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_USERS)
data class UserDb(
    @PrimaryKey
    val userId : String ,
    val gender : String ,
    val nameFirst : String ,
    val nameLast : String ,
    val location : String ,
    val coordinatesLatitude : String ,
    val coordinatesLongitude : String ,
    val email : String ,
    @Embedded(prefix = DatabaseConstants.PREFIX_DOB)
    val dob : DateWithAgeDb ,
    @Embedded(prefix = DatabaseConstants.PREFIX_REGISTERED)
    val registered : DateWithAgeDb ,
    val phone : String ,
    val picture : String
)
