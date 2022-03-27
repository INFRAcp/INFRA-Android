package com.example.infraandroid.myinfo.myinfomodify.model

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MyInfoService {
    @PATCH("/user/profile/info/{id}")
    fun viewMyInfo(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Path("id") userId : String
    ): Call<ResponseViewMyInfoData>

    @PATCH("/user/profile/info/{id}")
    fun ModifyMyInfo(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Path("id") userId : String
    ): Call<RequestModifyMyInfoData>


}