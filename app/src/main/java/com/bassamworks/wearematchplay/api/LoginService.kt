package com.bassamworks.wearematchplay.api

import com.bassamworks.wearematchplay.constatnts.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {


    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST(Constants.API.PATH_URL_LOGIN)
    fun getUserToken(@Body userCredentials: UserCredentials): Call<UserToken>
}

data class UserToken(val api_token: String)

data class UserCredentials(val email: String, val password: String)