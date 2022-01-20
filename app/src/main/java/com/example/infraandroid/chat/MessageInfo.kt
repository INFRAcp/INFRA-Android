package com.example.infraandroid.chat

data class MessageInfo(
    var senderId: String = "",
    var message: String = "",
    var sendTime : String = "",
    var dateLine : Boolean = true,
)
