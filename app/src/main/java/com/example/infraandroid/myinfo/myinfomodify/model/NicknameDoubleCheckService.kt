package com.example.infraandroid.myinfo.myinfomodify.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NicknameDoubleCheckService {
    @POST("/user/valid-nickname")
    fun doublecheck(
        @Body body : RequestNicknameCheckData
    ): Call<ResponseNicknameCheckData>
}