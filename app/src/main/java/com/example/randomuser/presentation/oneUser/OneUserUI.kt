package com.example.randomuser.presentation.oneUser

import com.example.randomuser.domain.entities.DateWithAge
import com.example.randomuser.domain.entities.GenderEnum
import com.example.randomuser.domain.entities.User

data class OneUserUi(
    val userId : String ,
    val gender : GenderEnum ,
    val nameFirst : String ,
    val nameLast : String ,
    val location : String ,
    val coordinatesLatitude : String ,
    val coordinatesLongitude : String ,
    val email : String ,
    val dob : DateWithAge ,
    val registered : DateWithAge ,
    val phone : String ,
    val picture : String ,
)

fun User.toOneUserUi() = OneUserUi(
    userId = userId ,
    gender = gender ,
    nameFirst = nameFirst ,
    nameLast = nameLast ,
    location = location ,
    coordinatesLatitude = coordinatesLatitude ,
    coordinatesLongitude = coordinatesLongitude ,
    email = email ,
    dob = dob ,
    registered = registered ,
    phone = phone ,
    picture = picture
)
