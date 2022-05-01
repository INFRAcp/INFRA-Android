package com.example.infraandroid.myinfo.myinfomodify.model

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MyInfoService {
    @GET("/user/profile/info/{id}")
    fun viewMyInfo(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Path("id") userId : String
    ): Call<ResponseViewMyInfoData>

    @Multipart
    @PATCH("/user/profile/info/{id}")
    fun modifyMyInfo(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Path("id") userId : String,
        @Part("user_nickname") userNickName : RequestBody,
        @Part("user_prPhoto") userProfileStatus : RequestBody,
        @Part images: MultipartBody.Part?
    ): Call<ResponseModifyMyInfoData>

}