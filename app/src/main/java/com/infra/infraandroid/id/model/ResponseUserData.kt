package com.infra.infraandroid.id.model

import com.google.gson.annotations.SerializedName

data class ResponseUserData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Data?
){
    data class Data(
        @SerializedName("user_id")
        val userId : String,
        val jwt : String,
    )
}
