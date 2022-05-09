package com.infra.infraandroid.myinfo.myideamanage.model

data class ResponseDeleteProjectData(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
){
    data class Result(
        val comment: String
    )
}