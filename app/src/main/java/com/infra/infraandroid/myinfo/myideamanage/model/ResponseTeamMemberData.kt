package com.infra.infraandroid.myinfo.myideamanage.model

data class ResponseTeamMemberData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ArrayList<Result>
){
    data class Result(
        val user_id: String,
        val user_nickname: String,
        val user_prPhoto: String,
        val pj_inviteStatus: String
    )
}