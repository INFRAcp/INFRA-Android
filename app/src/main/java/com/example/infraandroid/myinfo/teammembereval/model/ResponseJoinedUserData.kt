package com.example.infraandroid.myinfo.teammembereval.model

import com.google.gson.annotations.SerializedName

data class ResponseJoinedUserData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<Result>,
){
    data class Result(
        @SerializedName("user_nickname")
        val nickName : String,
        @SerializedName("user_prPhoto")
        val profileImg : String,
    )
}
