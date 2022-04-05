package com.example.infraandroid.id.model

import com.google.gson.annotations.SerializedName

data class RequestPWData(
    @SerializedName("user_phone")
    var userPhone : String
)
