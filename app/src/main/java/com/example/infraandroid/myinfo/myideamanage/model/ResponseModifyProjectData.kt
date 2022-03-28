package com.example.infraandroid.myinfo.myideamanage.model

data class ResponseModifyProjectData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
){
    data class Result(
        val pj_string: String
    )
}