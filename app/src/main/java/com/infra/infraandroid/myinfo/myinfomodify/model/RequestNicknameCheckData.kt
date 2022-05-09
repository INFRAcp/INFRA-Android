package com.infra.infraandroid.myinfo.myinfomodify.model

import com.google.gson.annotations.SerializedName

data class RequestNicknameCheckData(
    @SerializedName("user_nickname")
    val userNickname : String,
)
