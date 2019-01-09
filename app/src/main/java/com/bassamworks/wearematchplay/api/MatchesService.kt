package com.bassamworks.wearematchplay.api

import com.bassamworks.wearematchplay.constatnts.Constants
import com.bassamworks.wearematchplay.models.api.matches.MatchesResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MatchesService {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET(Constants.API.PATH_URL_MATCHES)
    fun getMatches(@Query("page") page: Int): Call<MatchesResponse>

    companion object {
        private const val TAG = "Matches Service"

        operator fun invoke(token: String): MatchesService {
            val requestInterceptor = Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.API.API_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MatchesService::class.java)
        }
    }

}