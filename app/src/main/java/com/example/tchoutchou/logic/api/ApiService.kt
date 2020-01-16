package com.example.tchoutchou.logic.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiInterface {
    @Headers("Authorization: 3b036afe-0110-4202-b9ed-99718476c2e0")
    @GET("coverage/sandbox/lines/line%3ARAT%3AM1/stop_areas")
    fun getStation(): Call<StationsModel>
}

class ApiService {
    companion object {
        //@Headers("Authorization: 3b036afe-0110-4202-b9ed-99718476c2e0")
        //@GET("coverage/sandbox/lines/line%3ARAT%3AM1/stop_areas")
        fun create(): ApiInterface {
            return Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.navitia.io/v1/")
                .build()
                .create(ApiInterface::class.java)
        }
    }
}