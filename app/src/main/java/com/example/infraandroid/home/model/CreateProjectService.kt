package com.example.infraandroid.home.model

import com.example.infraandroid.category.model.ResponseLookUpAllProjectData
import com.example.infraandroid.id.model.RequestLoginData
import com.example.infraandroid.id.model.ResponseLoginData
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
}