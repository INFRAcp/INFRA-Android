package com.infra.infraandroid.home.model

import com.infra.infraandroid.category.model.ResponseLookUpAllProjectData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface CreateProjectService {
    @Multipart
    @POST("/project/registration")
    fun postCreateProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Part ("jsonList") jsonList: RequestBody,
        @Part images: MultipartBody.Part?,
    ): Call<ResponseCreateProjectData>

    @GET("/project/inquiry?")
    fun searchProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Query("search") searchKeyword : String,
        @Query("user_id") userId : String
    ): Call<ResponseLookUpAllProjectData>

    @GET("/project/hot?")
    fun hotProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Query("user_id") userId : String
    ) : Call<ResponseHotProjectData>
}