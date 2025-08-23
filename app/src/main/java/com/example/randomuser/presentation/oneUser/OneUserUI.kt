package com.example.randomuser.presentation.oneUser

import com.example.randomuser.domain.entities.GenderEnum
import com.example.randomuser.domain.entities.User
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class OneUserUi(
    val userId : String ,
    val gender : GenderEnum ,
    val nameFirst : String ,
    val nameLast : String ,
    val location : String ,
    val coordinatesLatitude : String ,
    val coordinatesLongitude : String ,
    val email : String ,
    val dobDate : String ,
    val dobAge : String ,
    val registeredDate : String ,
    val registeredAge : String ,
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
    dobDate = formatDate(dob.date) ,
    dobAge = formatAge(dob.age) ,
    registeredDate = formatDate(registered.date) ,
    registeredAge = formatAge(registered.age) ,
    phone = phone ,
    picture = picture
)

fun formatDate(dateString : String) : String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" , Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM yyyy" , Locale.getDefault())
        val date = inputFormat.parse(dateString)
        outputFormat.format(date ?: Date())
    } catch (e : Exception) {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd" , Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy" , Locale.getDefault())
            val date = inputFormat.parse(dateString)
            outputFormat.format(date ?: Date())
        } catch (e : Exception) {
            dateString
        }
    }
}

fun formatAge(age : Int) : String {
    return "($age years)"
}