package com.example.infraandroid.myinfo.api

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileViewService {
    @GET("/user/profile/{회원 id}")
    fun getProfile(@Path("회원 id") userid: String): retrofit2.Call<ResponseProfileViewData>
}