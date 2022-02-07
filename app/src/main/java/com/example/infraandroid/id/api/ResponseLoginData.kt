package com.example.infraandroid.id.api

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
        val jwt : String,
    )
}
