package com.example.infraandroid.chat.model

data class MessageInfo(
    var senderId: String = "",
    var message: String = "",
    var sendTime : String = "",
    var profileImg : String? = "",
    var dateLine : Boolean = true,
)
