package com.infra.infraandroid.id.model

data class ResponseSMSData(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: Data?
){
    data class Data(
        val requestId : String,
        val localDateTime : String,
        val statusCode : String,
        val statusName : String,
        val certifyValue : Int
    )
}
