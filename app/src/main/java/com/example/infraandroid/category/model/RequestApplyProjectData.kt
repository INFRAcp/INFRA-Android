package com.example.infraandroid.category.model

import com.google.gson.annotations.SerializedName

data class RequestApplyProjectData(
    @SerializedName("pj_num")
    val projectNum : Int,
    @SerializedName("user_id")
    val userId : String,
)