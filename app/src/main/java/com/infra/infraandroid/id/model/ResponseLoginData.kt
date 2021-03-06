package com.infra.infraandroid.id.model

import com.google.gson.annotations.SerializedName

data class ResponseLoginData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: LoginData?
){
    data class LoginData(
        @SerializedName("user_id")
        val userId : String,
        @SerializedName("jwtAccess")
        val jwt : String,
        @SerializedName("user_name")
        val userName : String,
        @SerializedName("user_nickname")
        val userNickName : String,
        @SerializedName("jwtRefreshIdx")
        val refreshToken : Int,
        @SerializedName("user_prPhoto")
        val userProfileImg : String
    )
}
