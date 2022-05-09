package com.infra.infraandroid.id.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDoubleCheckService {
    @GET("/user/valid-id/{user_id}")
    fun doubleCheck(@Path("user_id") user_id: String): Call<ResponseCheckUserIdData>
}