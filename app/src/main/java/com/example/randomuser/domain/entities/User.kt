package com.example.randomuser.domain.entities

data class User(
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
