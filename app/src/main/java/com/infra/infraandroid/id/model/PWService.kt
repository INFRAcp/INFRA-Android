package com.infra.infraandroid.id.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PATCH

interface PWService {
    @PATCH("/user/reset-pw")
    fun findPWInfo(
        @Body body : RequestPWData
    ): Call<ResponsePWData>
}