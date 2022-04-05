package com.example.infraandroid.category.model

data class ResponseViewUserProfileData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: ArrayList<Result>?,
){
    data class Result(
        val user_id: String,
        val user_prPhoto: String,
        val user_nickname: String,
        val user_prAbility: ArrayList<String>,
        val user_grade: Float,
        val user_prKeyword: ArrayList<String>,
    )
}
