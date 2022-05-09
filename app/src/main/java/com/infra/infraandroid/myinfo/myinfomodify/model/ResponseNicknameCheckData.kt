package com.infra.infraandroid.myinfo.myinfomodify.model

data class ResponseNicknameCheckData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: String
)
