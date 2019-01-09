package com.bassamworks.wearematchplay.api

import com.bassamworks.wearematchplay.constatnts.Constants
import com.bassamworks.wearematchplay.models.api.login.UserCredentials
import com.bassamworks.wearematchplay.models.api.login.UserTokenResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {


    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST(Constants.API.PATH_URL_LOGIN)
    fun getUserToken(@Body userCredentials: UserCredentials): Call<UserTokenResponse>

    companion object {
        operator fun invoke(): LoginService {
            return Retrofit.Builder()
                .baseUrl(Constants.API.API_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginService::class.java)
        }
    }
}
