package com.example.infraandroid.id.api

data class ResponseCheckUserIdData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: String,
)
