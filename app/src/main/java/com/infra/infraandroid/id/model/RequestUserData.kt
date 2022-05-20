package com.infra.infraandroid.id.model

import com.google.gson.annotations.SerializedName

data class RequestUserData(
    @SerializedName("user_id")
    var userId : String,
    @SerializedName("user_pw")
    var userPw : String,
    @SerializedName("user_phone")
    var userPhone : String,
    @SerializedName("user_email")
    var userEmail : String,
    @SerializedName("user_nickname")
    var userNickName : String
)
