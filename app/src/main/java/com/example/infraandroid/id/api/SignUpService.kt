package com.example.infraandroid.id.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("/user/sign-up")
    fun postSignUp(
        @Body body : RequestUserData
    ): Call<ResponseUserData>
}