package com.example.infraandroid.category.model

data class ResponseApplyProjectData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Result?
){
    data class Result(
        val comment: String,
    )
}