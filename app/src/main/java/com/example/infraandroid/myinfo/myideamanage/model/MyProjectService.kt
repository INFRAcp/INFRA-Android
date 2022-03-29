package com.example.infraandroid.myinfo.myideamanage.model

import com.example.infraandroid.category.model.ResponseLookUpAllProjectData
import retrofit2.Call
import retrofit2.http.*

interface MyProjectService {
    @GET("/project/myProject?")
    fun viewMyProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Query("user_id") userId : String
    ): Call<ResponseMyProjectListData>

    @PATCH("/project/modify")
    fun modifyProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int
    ): Call<ResponseModifyProjectData>

    @GET("/project/apply-list?")
    fun viewProjectApply(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Query("user_id") userId: String,
        @Query("pj_num") pjNum: Int?
    ): Call<ResponseViewProjectApplyData>

    @PATCH("/project/member")
    fun acceptTeamMember(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Body body: RequestAcceptData
    ): Call<ResponseAcceptData>

    @HTTP(method="DELETE", hasBody=true, path="/project/del")
    fun deleteProject(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Header("X-REFRESH-TOKEN") refreshToken: Int,
        @Body body: RequestDeleteProjectData
    ): Call<ResponseDeleteProjectData>
}