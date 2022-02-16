package com.example.infraandroid.id.model

import com.google.gson.annotations.SerializedName

data class RequestUserData(
    @SerializedName("user_id")
    var userId : String,
    @SerializedName("user_pw")
    var userPw : String,
    @SerializedName("user_nickname")
    val userNickname : String,
    @SerializedName("user_phone")
    var userPhone : String,
    @SerializedName("user_email")
    val userEmail : String,
    @SerializedName("user_name")
    var userName : String,
)
