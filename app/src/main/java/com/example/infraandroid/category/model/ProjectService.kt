package com.example.infraandroid.category.model

import com.example.infraandroid.myinfo.myideamanage.model.ResponseModifyProjectData
import retrofit2.Call
import retrofit2.http.*

interface ProjectService {
    @GET("/project/inquiry?")
    fun getLookUpAllProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Query("user_id") user_id : String
    ): Call<ResponseLookUpAllProjectData>

    @POST("/project/apply")
    fun postApplyProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Body body : RequestApplyProjectData
    ): Call<ResponseApplyProjectData>

    @GET("/project/contact")
    fun viewProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Query("pj_num") projectNum : Int?,
        @Query("user_id") userId : String
    ): Call<ResponseViewIdeaData>

    @GET("/user/profile/all")
    fun viewUserProfile(

    ): Call<ResponseViewUserProfileData>
}