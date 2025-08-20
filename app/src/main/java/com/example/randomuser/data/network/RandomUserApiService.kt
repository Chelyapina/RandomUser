package com.example.randomuser.data.network

import com.example.randomuser.data.network.models.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApiService {
    @GET(API_ENDPOINT)
    suspend fun getUsers(
        @Query(PARAM_RESULTS) results : Int = DEFAULT_RESULTS ,
        @Query(PARAM_EXCLUDE) excludeFields : String = EXCLUDE_FIELDS
    ) : ResponseDto

    companion object {
        private const val BASE_PATH = "api/"
        private const val NO_INFO_PARAM = "noinfo"
        private const val API_ENDPOINT = "$BASE_PATH?$NO_INFO_PARAM"

        const val DEFAULT_RESULTS = 15
        const val EXCLUDE_FIELDS = "login,cell,nat,id"

        const val PARAM_RESULTS = "results"
        const val PARAM_EXCLUDE = "exc"
    }
}