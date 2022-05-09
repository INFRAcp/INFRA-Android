package com.infra.infraandroid.id.model

data class RequestSMSData(
    val recipientPhoneNumber : String,
    val title : String,
    val content : String,
)
