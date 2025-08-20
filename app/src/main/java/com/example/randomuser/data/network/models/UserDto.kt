package com.example.randomuser.data.network.models

data class UserDto(
    val gender : String ,
    val name : NameDto ,
    val location : LocationDto ,
    val email : String ,
    val dob : DateWithAgeDto ,
    val registered : DateWithAgeDto ,
    val phone : String ,
    val picture : PictureDto ,
)
