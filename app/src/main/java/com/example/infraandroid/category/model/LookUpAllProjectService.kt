package com.example.infraandroid.category.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface LookUpAllProjectService {
    @GET("/project/inquiry")
    fun getLookUpAllProject(
        @Header("user_id") user_id: String
    ): Call<ResponseLookUpAllProjectData>
}