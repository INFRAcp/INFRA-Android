package com.example.infraandroid.category.model

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
        @Body body : RequestApplyProjectData
    ): Call<ResponseApplyProjectData>

    @GET("/project/contact")
    fun viewProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: String,
        @Query("pj_num") projectNum : Int,
        @Query("user_id") userId : String
    ): Call<ResponseViewIdeaData>
}