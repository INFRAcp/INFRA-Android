package com.infra.infraandroid.home.model

import com.google.gson.annotations.SerializedName

data class ResponseCreateProjectData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ResponseData?
){
    data class ResponseData(
        @SerializedName("pj_name")
        val projectTitle : String
    )
}