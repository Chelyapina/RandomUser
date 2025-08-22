package com.example.randomuser.presentation.users

import com.example.randomuser.domain.entities.GenderEnum
import com.example.randomuser.domain.entities.User

data class UserUi(
    val userId : String ,
    val gender : GenderEnum ,
    val nameFirst : String ,
    val nameLast : String ,
    val location : String ,
    val phone : String ,
    val picture : String ,
)

fun User.toUserUI() = UserUi(
    userId = userId ,
    gender = gender ,
    nameFirst = nameFirst ,
    nameLast = nameLast ,
    location = location ,
    phone = phone ,
    picture = picture
)
