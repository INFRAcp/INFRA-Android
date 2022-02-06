package com.example.infraandroid.id.api

data class RequestSMSData(
    val recipientPhoneNumber : String,
    val title : String,
    val content : String,
)
