package com.infra.infraandroid.id.model

data class ResponseCheckUserIdData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: String,
)
