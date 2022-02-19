package com.example.infraandroid.category.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApplyProjectService {
    @POST("/project/apply")
    fun postApplyProject(
        @Body body : RequestApplyProjectData
    ): Call<ResponseApplyProjectData>
}