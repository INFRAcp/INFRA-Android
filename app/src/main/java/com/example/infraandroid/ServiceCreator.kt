package com.example.infraandroid

import com.example.infraandroid.myinfo.api.ProfileViewService
import com.example.infraandroid.id.api.LoginService
import com.example.infraandroid.id.api.SMSService
import com.example.infraandroid.id.api.SignUpService
import com.example.infraandroid.id.api.UserDoubleCheckService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://qbeom.shop"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userDoubleCheckService : UserDoubleCheckService = retrofit.create(UserDoubleCheckService::class.java)
    val signUpService : SignUpService = retrofit.create(SignUpService::class.java)
    val loginService : LoginService = retrofit.create(LoginService::class.java)
    val sendSMSService : SMSService = retrofit.create(SMSService::class.java)
    val profileViewService : ProfileViewService = retrofit.create(ProfileViewService::class.java)
}