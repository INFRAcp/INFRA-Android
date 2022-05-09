package com.infra.infraandroid.myinfo.myideamanage.model

data class ResponseViewProjectApplyData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ArrayList<Result>
){
    data class Result(
        val pj_inviteStatus: String,
        val user_id: String,
        val user_nickname: String,
        val user_prphoto: String,
        val user_grade: String
    )
}