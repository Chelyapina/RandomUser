package com.example.randomuser.data.mappers

import com.example.randomuser.data.storage.entities.DateWithAgeDb
import com.example.randomuser.data.storage.entities.UserDb
import com.example.randomuser.domain.entities.DateWithAge
import com.example.randomuser.domain.entities.GenderEnum
import com.example.randomuser.domain.entities.User

class UserDbMapper {
    fun mapToDb(user : User) : UserDb {
        return UserDb(
            userId = user.userId ,
            gender = user.gender.toString() ,
            nameFirst = user.nameFirst ,
            nameLast = user.nameLast ,
            location = user.location ,
            coordinatesLatitude = user.coordinatesLatitude ,
            coordinatesLongitude = user.coordinatesLongitude ,
            email = user.email ,
            dob = DateWithAgeDb(user.dob.date , user.dob.age) ,
            registered = DateWithAgeDb(user.registered.date , user.registered.age) ,
            phone = user.phone ,
            picture = user.picture
        )
    }

    fun mapFromDb(userDb : UserDb) : User {
        return User(
            userId = userDb.userId ,
            gender = userDb.gender.toGenderEnum() ,
            nameFirst = userDb.nameFirst ,
            nameLast = userDb.nameLast ,
            location = userDb.location ,
            coordinatesLatitude = userDb.coordinatesLatitude ,
            coordinatesLongitude = userDb.coordinatesLongitude ,
            email = userDb.email ,
            dob = DateWithAge(userDb.dob.date , userDb.dob.age) ,
            registered = DateWithAge(userDb.registered.date , userDb.registered.age) ,
            phone = userDb.phone ,
            picture = userDb.picture
        )
    }

    fun String.toGenderEnum() : GenderEnum = enumValueOf(this.uppercase())
    fun mapToDbList(users : List<User>) : List<UserDb> = users.map { mapToDb(it) }
    fun mapFromDbList(usersDb : List<UserDb>) : List<User> = usersDb.map { mapFromDb(it) }
}