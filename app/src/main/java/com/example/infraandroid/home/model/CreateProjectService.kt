package com.example.infraandroid.home.model

import com.example.infraandroid.id.model.RequestLoginData
import com.example.infraandroid.id.model.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CreateProjectService {
    @POST("/project/registration")
    fun postCreateProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Body body: RequestCreateProjectData
    ): Call<ResponseCreateProjectData>
}