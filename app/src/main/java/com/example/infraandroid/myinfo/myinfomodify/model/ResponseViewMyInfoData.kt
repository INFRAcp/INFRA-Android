package com.example.infraandroid.myinfo.myinfomodify.model

data class ResponseViewMyInfoData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Result,
){
    data class Result(
        val user_nickname: String,
        val user_prPhoto: String,
    )
}

