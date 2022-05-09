package com.infra.infraandroid.myinfo.myintroductionpage.model

data class ResponseProfileViewData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Data?
){
    data class Data(
        val user_nickname : String,
        val user_grade : Double,
        val user_prPhoto : String,
        val user_prProfile : String,
        val user_prAbility : Int,
        val user_prLink : String,
        val user_prKeyword : String,
        val pj_name : String
    )
}

