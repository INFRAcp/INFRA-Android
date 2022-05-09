package com.infra.infraandroid.id.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SMSService {
    @POST("/sms/send")
    fun postSendSMS(
        @Body body : RequestSMSData
    ): Call<ResponseSMSData>
}