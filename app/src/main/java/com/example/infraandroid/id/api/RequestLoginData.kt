package com.example.infraandroid.id.api

import com.google.gson.annotations.SerializedName

data class RequestLoginData(
    @SerializedName("user_id")
    val userId : String,
    @SerializedName("user_pw")
    val userPw : String,
)
