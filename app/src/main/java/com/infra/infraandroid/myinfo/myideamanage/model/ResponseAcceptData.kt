package com.infra.infraandroid.myinfo.myideamanage.model

data class ResponseAcceptData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
){
    data class Result(
        val pj_inviteStatus: String
    )
}