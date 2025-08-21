package com.example.randomuser.data.mappers

import com.example.randomuser.data.network.models.LocationDto
import com.example.randomuser.data.network.models.ResponseDto
import com.example.randomuser.data.network.models.UserDto
import com.example.randomuser.domain.entities.DateWithAge
import com.example.randomuser.domain.entities.GenderEnum
import com.example.randomuser.domain.entities.User

class UserNetworkMapper {
    fun mapFromDto(dto : UserDto) : User {
        return User(
            userId = dto.login.uuid ,
            gender = dto.gender.toGenderEnum() ,
            nameFirst = dto.name.first ,
            nameLast = dto.name.last ,
            location = dto.location.formatAddress() ,
            coordinatesLatitude = dto.location.coordinates.latitude ,
            coordinatesLongitude = dto.location.coordinates.longitude ,
            email = dto.email ,
            dob = DateWithAge(dto.dob.date , dto.dob.age) ,
            registered = DateWithAge(dto.registered.date , dto.registered.age) ,
            phone = dto.phone ,
            picture = dto.picture.large
        )
    }

    fun mapFromResponse(response : ResponseDto) : List<User> =
        response.results.map { mapFromDto(it) }


    private fun String.toGenderEnum() : GenderEnum =
        when (this.lowercase()) {
            "female" -> GenderEnum.FEMALE
            "male" -> GenderEnum.MALE
            else -> throw IllegalArgumentException("Unknown gender: $this")
        }

    private fun LocationDto.formatAddress() : String =
        "${this.street.number}, " +
                "${this.street.name}, " +
                "${this.city}, " +
                "${this.state}, " +
                this.country
}