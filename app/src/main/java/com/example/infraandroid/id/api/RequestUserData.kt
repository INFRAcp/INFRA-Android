package com.example.infraandroid.id.api

import com.google.gson.annotations.SerializedName

data class RequestUserData(
    @SerializedName("user_id")
    val userId : String,
    @SerializedName("user_pw")
    val userPw : String,
    @SerializedName("user_nickname")
    val userNickname : String,
    @SerializedName("user_phone")
    val userPhone : String,
    @SerializedName("user_email")
    val userEmail : String,
    @SerializedName("user_name")
    val userName : String,
)
