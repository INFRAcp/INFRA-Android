package com.infra.infraandroid.myinfo.teammembereval.model

import com.google.gson.annotations.SerializedName

data class RequestJoinedUserData(
    @SerializedName("pj_num")
    val projectIdx : Int
)
