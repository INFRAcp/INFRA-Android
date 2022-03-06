package com.example.infraandroid.myinfo.teammembereval.model

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface TeamMemberService {
    @POST("/project/team?")
    fun lookUpAllJoinedUser(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Query("user_id") user_id : String
    ): Call<ResponseJoinedUserData>
}