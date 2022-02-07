package com.example.infraandroid.id.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/user/log-in")
    fun postLogin(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>
}