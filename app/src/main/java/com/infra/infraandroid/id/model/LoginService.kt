package com.infra.infraandroid.id.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/user/log-in")
    fun postLogin(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>
}